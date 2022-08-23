package com.example.magiclinkdemo

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.magiclinkdemo.App.Companion.b2cApp
import com.example.magiclinkdemo.App.Companion.users
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalClientException
import com.microsoft.identity.client.exception.MsalException
import com.microsoft.identity.client.exception.MsalServiceException
import com.microsoft.identity.client.exception.MsalUiRequiredException


class B2CActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b2_cactivity)

        val window = window
        val decorView = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)
        wic.isAppearanceLightStatusBars = true // true or false as desired.
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white))

        //b2c interate
//        intent?.data?.let {
//            magicLinkFlow(it)
//        }
//        when (intent.getIntExtra("b2c", 0)) {
//            1 -> {
//                Log.e(TAG, "onCreate: b2c intent 1->edit profile")
//                b2cEditProfileFlow()
//            }
//            2 -> {
//                Log.e(TAG, "onCreate: b2c intent 2->edit profile")
//                b2generalFlow()
//            }
//            3 -> {
//                Log.e(TAG, "onCreate: b2c intent 3->edit profile")
//                b2cLogoutFlow()
//            }
//        }
        PublicClientApplication.createMultipleAccountPublicClientApplication(this,
            R.raw.msal_config,
            object : IPublicClientApplication.IMultipleAccountApplicationCreatedListener {
                override fun onCreated(application: IMultipleAccountPublicClientApplication) {
                    b2cApp = application
                    Log.d("deeplink", "init $application, b2c $b2cApp")
                    loadAccounts()
                    intent?.data?.let {
                        magicLinkFlow(it)
                    }
                    when (intent.getIntExtra("b2c", 0)) {
                        1 -> {
                            Log.e(TAG, "onCreate: b2c intent 1->edit profile")
                            b2cEditProfileFlow()
                        }
                        2 -> {
                            b2generalFlow()
                        }
                        3 -> {
                            b2cLogoutFlow()
                        }
                    }

                }

                override fun onError(exception: MsalException) {
                    displayError(exception)
                    Log.d("deeplink", "error msg:${exception.printStackTrace()}")
                }
            })

    }



    fun b2generalFlow() {
        val policy = B2CConfiguration.Policies[0]//general flow
        Log.e(TAG, "b2generalFlow: ${B2CConfiguration.getAuthorityFromPolicyName(policy)}")
        val parameters = AcquireTokenParameters.Builder()
            .startAuthorizationFromActivity(this)
            .fromAuthority(B2CConfiguration.getAuthorityFromPolicyName(policy))
            .withScopes(B2CConfiguration.scopes)
            .withPrompt(Prompt.WHEN_REQUIRED)
            .withCallback(authInteractiveCallback)
            .build()
        b2cApp!!.acquireToken(parameters)
     //   loadAccounts()
    }

    fun b2cEditProfileFlow() {
        if (b2cApp == null) {
            Log.e(TAG, "b2cLogoutFlow: b2capp is null ")
            return
        }
        Log.e(TAG, "b2cEditProfileFlow: users :${users?.value}", )
        users?.observe(this) {
            if (it.isEmpty()) {
                Log.e(TAG, "b2cLogoutFlow: no user ")
               // return
            } else {
                Log.e(
                    TAG,
                    "b2cEditProfileFlow: user is ${it[0]}, users siz: ${it.size}",
                )
                val policy = B2CConfiguration.Policies[3]//edit profile
                val email = ArrayList<Pair<String, String>>()//store the email
                val userEmail = it[0].email
                email.add(android.util.Pair("email", userEmail))
                Log.e(TAG, "email is $email")
                Log.e(
                    TAG,
                    "testEditProfile: policy:${B2CConfiguration.getAuthorityFromPolicyName(policy)}",
                )
                b2cLogoutFlow()
                Log.e(TAG, "testEditProfile:clear user i-account")

                val parameters = getParameter(policy, email)

                b2cApp!!.acquireToken(parameters!!)
            }
        }
    }

    fun b2cLogoutFlow() {
        if (b2cApp == null) {
            Log.e(TAG, "b2cLogoutFlow: b2capp is null", )
            return
        }
        users?.observe(this) {
            if (it.isEmpty()) {
                Log.e(TAG, "b2cLogoutFlow: no user ")
                // return
            } else {
                val selectedUser = it[0]
                selectedUser.signOutAsync(b2cApp!!,
                    object : IMultipleAccountPublicClientApplication.RemoveAccountCallback {
                        override fun onRemoved() {
                            Log.e(
                                "b2cActivity",
                                "onRemoved: successful, user size is ${it[0]}, user name: ${it[0].displayName}",
                            )
                        }

                        override fun onError(exception: MsalException) {
                            displayError(exception)
                        }
                    })
            }
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    fun magicLinkFlow(uri: Uri) {
        //val uri = intent?.data
        var policy = B2CConfiguration.Policies[0]//general flow
        val eparameters = ArrayList<Pair<String, String>>()//store the id_token_hint
        uri.let {
            val idToken = uri!!.getQueryParameter("id_token_hint")
            Log.d(TAG, "id token hint is $idToken")
            eparameters.add(Pair("id_token_hint", idToken))
            Log.d(TAG, "eparameter is $eparameters")
            policy = B2CConfiguration.Policies[1]//magic link
            Log.d(TAG, "policy is $policy")
            val parameters = getParameter(policy, eparameters)
            b2cApp!!.acquireToken(parameters!!)
        }
        //loadAccounts()
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


    private val authInteractiveCallback: AuthenticationCallback
        get() = object : AuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d(TAG, "Successfully authenticated")
                /* display result info */
                displayResult(authenticationResult)
                loadAccounts()
                //return to for you feed
                val intent = Intent(this@B2CActivity, MainActivity2::class.java)
                startActivity(intent)
                Log.e(TAG, "onSuccess: intent to main activity" )

                //b2c

                // users= B2CUser.getB2CUsersFromAccountList(authenticationResult.account as List<IAccount>)

            }

            override fun onError(exception: MsalException) {
                val B2C_PASSWORD_CHANGE = "AADB2C90118"
                if (exception.message!!.contains(B2C_PASSWORD_CHANGE)) {
                    Log.d(TAG, "${exception.printStackTrace()}")
                    return
                }

                Log.d(TAG, "Authentication failed: $exception")
                displayError(exception)
                if (exception is MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                    Log.d(TAG, "${exception.printStackTrace()}")

                } else if (exception is MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                    Log.d(TAG, "${exception.printStackTrace()}")
                }
            }

            override fun onCancel() {
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.")
            }
        }

    private fun displayResult(result: IAuthenticationResult) {
        val output = """
         Access Token :${result.accessToken}
         Scope : ${result.scope}
         Expiry : ${result.expiresOn}
         Tenant ID : ${result.tenantId}
         Policy :${result.account.authority}
         """.trimIndent()
        Log.d(
            TAG,
            "$output , user info is \nname: ${result.account.username} \nuser claim: ${result.account.claims} \nid:${result.account.id}"
        )
        Toast.makeText(this, "Hello ${result.account.username}", Toast.LENGTH_LONG)
            .show()
        //save user info in shared preference
        sharedPreferences = getSharedPreferences("current_user", MODE_PRIVATE)
        var editor = sharedPreferences!!.edit()
        editor.putString("user_id", result.account.id)
        var claims = result.account.claims
        for (s in claims!!.keys) {
            editor.putString(s, claims.get(s).toString())
        }
        editor.apply()
        //store in db

        //store in b2c user
        for (s in claims.keys) {
            Log.e(TAG, "displayResult: key is $s , content is ${claims.get(s).toString()}")
        }

    }

    private val authSilentCallback: SilentAuthenticationCallback
        private get() = object : SilentAuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                Log.d(TAG, "Successfully authenticated")
                /* Successfully got a token. */
                displayResult(authenticationResult)
            }

            override fun onError(exception: MsalException) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: $exception")
                displayError(exception)
                if (exception is MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception is MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                } else if (exception is MsalUiRequiredException) {
                    /* Tokens expired or no session, retry with interactive */
                }
            }
        }

    private fun displayError(exception: Exception) {
        Log.d(TAG, "$exception")
    }

    /**
     * Load signed-in accounts, if there's any.
     */
    private fun loadAccounts() {
        if (b2cApp == null) {
            return
        }
        b2cApp!!.getAccounts(object : IPublicClientApplication.LoadAccountsCallback {
            override fun onTaskCompleted(result: List<IAccount>) {
                users?.postValue(B2CUser.getB2CUsersFromAccountList(result))
                // users = B2CUser.getB2CUsersFromAccountList(result)
                Log.e(TAG, "onTaskCompleted: add user, user: ${users?.value}", )
            }

            override fun onError(exception: MsalException) {
                displayError(exception)
            }
        })
    }



    companion object {
        private val TAG = "B2CActivity"

    }
}
//        PublicClientApplication.createMultipleAccountPublicClientApplication(this,
//            R.raw.msal_config,
//            object : IPublicClientApplication.IMultipleAccountApplicationCreatedListener {
//                override fun onCreated(application: IMultipleAccountPublicClientApplication) {
//                    b2cApp = application
//                    Log.d("deeplink", "init $application, b2c $b2cApp")
//                    loadAccounts()
//                    intent?.data?.let {
//                        magicLinkFlow(it)
//                    }
//                    when (intent.getIntExtra("b2c", 0)) {
//                        1 -> {
//                            Log.e(TAG, "onCreate: b2c intent 1->edit profile")
//                            b2cEditProfileFlow()
//                        }
//                        2 -> {
//                            b2generalFlow()
//                        }
//                        3 -> {
//                            b2cLogoutFlow()
//                        }
//                    }
//
//                }
//
//                override fun onError(exception: MsalException) {
//                    displayError(exception)
//                    Log.d("deeplink", "error msg:${exception.printStackTrace()}")
//
//                }
//            })
//        Log.e(TAG, "onCreate: ")
//
//        Log.e(TAG, "onCreate: ${intent.data}")
//  loadAccounts()
//        if (users?.isEmpty() == true) {
//            Log.e(TAG, "b2cLogoutFlow: no user ")
//            return
//        } else {
//            Log.e(TAG, "b2cEditProfileFlow: user is ${users?.get(0)}, users siz: ${users?.size}", )
//            val policy = B2CConfiguration.Policies[3]//edit profile
//            val email = ArrayList<Pair<String, String>>()//store the email
//            val userEmail = users?.get(0)!!.email
//            email.add(android.util.Pair("email", userEmail))
//            Log.e(TAG, "email is $email")
//            Log.e(
//                TAG,
//                "testEditProfile: policy:${B2CConfiguration.getAuthorityFromPolicyName(policy)}",
//            )
//            b2cLogoutFlow()
//            Log.e(TAG, "testEditProfile:clear user i-account")
//
//        val parameters = getParameter(policy, email)
//
//        b2cApp!!.acquireToken(parameters!!)
//    }