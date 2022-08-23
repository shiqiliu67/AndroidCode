package com.example.magiclinkdemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import com.example.magiclinkdemo.B2CConfiguration.getAuthorityFromPolicyName
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalClientException
import com.microsoft.identity.client.exception.MsalException
import com.microsoft.identity.client.exception.MsalServiceException
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var b2cApp: IMultipleAccountPublicClientApplication? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initToolbar()
        //b2c interate
        PublicClientApplication.createMultipleAccountPublicClientApplication(this,
            R.raw.msal_config,
            object : IPublicClientApplication.IMultipleAccountApplicationCreatedListener {
                override fun onCreated(application: IMultipleAccountPublicClientApplication) {
                    b2cApp = application
                    Log.d("deeplink", "init $application, b2c ${b2cApp}")
                    //magic link
                    val url = intent?.data
                    CookieManager.getInstance().removeAllCookie()
                    CookieManager.getInstance().flush()
                    Log.e(TAG, "onResume: url is $url")
                    if (url != null) {
                        Log.e(TAG, "onResume:url $url")
                        magicLinkFlow(uri = url)
                    }
                }

                override fun onError(exception: MsalException) {
                    displayError(exception)
                    Log.d("deeplink", "error msg:${exception.printStackTrace()}")

                }
            })

    }
    fun magicLinkFlow(uri: Uri) {
        //val uri = intent?.data
        var policy = B2CConfiguration.Policies[0]//general flow
        val eparameters = ArrayList<Pair<String, String>>()//store the id_token_hint
        uri.let {
            val idToken = uri.getQueryParameter("id_token_hint")
            Log.d(TAG, "id token hint is $idToken")
            eparameters.add(Pair("id_token_hint", idToken))
            Log.d(TAG, "eparameter is $eparameters")
            policy = B2CConfiguration.Policies[1]//magic link
            Log.d(TAG, "policy is $policy")
            //b2cApp?.removeAccount()
//            users?.get(0)!!.clearSession(b2cApp!!)
            val parameters = getParameter(policy, eparameters)
            b2cApp!!.acquireToken(parameters!!)
        }
    }
    private fun getParameter(
        policy: String,
        eparameters: ArrayList<Pair<String, String>>
    ) =
        AcquireTokenParameters.Builder()
            .startAuthorizationFromActivity(this)
            .fromAuthority(B2CConfiguration.getAuthorityFromPolicyName(policy))
            .withScopes(B2CConfiguration.scopes)
            .withPrompt(Prompt.WHEN_REQUIRED)
            .withAuthorizationQueryStringParameters(eparameters)//only use for magic link flow
            .withCallback(authInteractiveCallback)
            .build()

    override fun onResume() {
        super.onResume()
      //  initializeUI()

    }

    private fun initializeUI() {

        var policy = B2CConfiguration.Policies[0]//general flow
        val eparameters = ArrayList<Pair<String, String>>()//store the id_token_hint
        if (b2cApp == null) {
            return
        }
        var parameters = AcquireTokenParameters.Builder()
            .startAuthorizationFromActivity(this@MainActivity)
            .fromAuthority(getAuthorityFromPolicyName(policy))
            .withScopes(B2CConfiguration.scopes)
            .withPrompt(Prompt.LOGIN)
            .withAuthorizationQueryStringParameters(eparameters)//only use for magic link flow
            .withCallback(authInteractiveCallback)
            .build()
        b2cApp!!.acquireToken(parameters)

        Log.e(TAG, "initializeUI: ${getAuthorityFromPolicyName(policy)}")
//        var uri = "https://B2CGPDEV.b2clogin.com/B2CGPDEV.onmicrosoft.com/oauth2/v2.0/authorize?p=B2C_1A_SIGNIN_OIDC&client_id=b43ce3dc-e3c3-4e9c-b534-e89b9b181c2c&nonce=defaultNonce&redirect_uri=myapp%3A%2F%2Fcom.android.tlapplication%2FxSLzBBuLJunOQPB89rtzM54FXx4%3D&scope=openid&response_type=code"
//        webview = findViewById<WebView>(R.id.webview)
//        webview.webViewClient = WebViewClient()
//      //  webview.loadUrl(uri)
//        Log.e(TAG, "initializeUI: loaduri", )
//        webview.loadUrl(uri).apply {
//            Log.e(TAG, "initializeUI: loadurl successful", )
////            var parameters = AcquireTokenParameters.Builder()
////                .startAuthorizationFromActivity(this@MainActivity)
////                .fromAuthority(getAuthorityFromPolicyName(policy))
////                .withScopes(B2CConfiguration.scopes)
////                .withPrompt(Prompt.LOGIN)
////                .withAuthorizationQueryStringParameters(eparameters)//only use for magic link flow
////                .withCallback(authInteractiveCallback)
////                .build()
////            b2cApp!!.acquireToken(parameters)
//        }

    }


    private val authInteractiveCallback: AuthenticationCallback
        private get() = object : AuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d(TAG, "Successfully authenticated")
                Log.d("deeplink", "on success")
                /* display result info */displayResult(authenticationResult)

                //intent user to choose topic

            }

            override fun onError(exception: MsalException) {
                val B2C_PASSWORD_CHANGE = "AADB2C90118"
                if (exception.message!!.contains(B2C_PASSWORD_CHANGE)) {
//                    txt_log!!.text = """
//                        The user clicks the 'Forgot Password' link in a sign-up or sign-in user flow.
//                        Your application needs to handle this error code by running a specific user flow that resets the password.
//                        """.trimIndent()
                    Log.d(TAG, "${exception.printStackTrace()}")
                    Log.d("deeplink", "on error ${exception.printStackTrace()}")

                    return
                }

                /* Failed to acquireToken */Log.d(TAG, "Authentication failed: $exception")
                displayError(exception)
                if (exception is MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                    Log.d(TAG, "${exception.printStackTrace()}")
                    Log.d("deeplink", "on error ${exception.printStackTrace()}")


                } else if (exception is MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                    Log.d(TAG, "${exception.printStackTrace()}")
                    Log.d("deeplink", "on error ${exception.printStackTrace()}")


                }
            }

            override fun onCancel() {
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.")
                Log.d("deeplink", "on cancel")

            }
        }

    private fun displayResult(result: IAuthenticationResult) {
        val output = """
         Access Token :${result.accessToken}
         Scope : ${result.scope}
         Expiry : ${result.expiresOn}
         Tenant ID : ${result.tenantId}
         
         """.trimIndent()
        Log.d(
            TAG,
            "$output , user info is \nname: ${result.account.username} \nuser claim: ${result.account.claims}"
        )
        //txt_log!!.text = output
        Toast.makeText(this, "Hello ${result.account.username}", Toast.LENGTH_LONG)
            .show()

    }


    private fun displayError(exception: Exception) {
        Toast.makeText(this, "Error: $exception", Toast.LENGTH_SHORT).show()
        Log.e("abc", "displayError: ")
    }

//    private fun initToolbar() {
//        val toolbar = findViewById<View>(R.id.toolbar_return) as Toolbar
//        setSupportActionBar(toolbar)
//        val actionbar = supportActionBar
//        actionbar!!.title = ""
//        actionbar.setDisplayHomeAsUpEnabled(true)
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_back)
//    }

    //handle back pressed button to previous
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val TAG = "MainActivity"
    }
}