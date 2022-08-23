package com.example.magiclinkdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.microsoft.identity.client.IAccount
import com.microsoft.identity.client.IMultipleAccountPublicClientApplication
import com.microsoft.identity.client.IPublicClientApplication
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.client.exception.MsalException

class App :Application(){
    companion object{
        var b2cApp : IMultipleAccountPublicClientApplication? = null
        var users: MutableLiveData<List<B2CUser>>? = null
        val TAG ="App"
    }

    override fun onCreate() {
        super.onCreate()
        //b2c interate
        PublicClientApplication.createMultipleAccountPublicClientApplication(this,
            R.raw.msal_config,
            object : IPublicClientApplication.IMultipleAccountApplicationCreatedListener {
                override fun onCreated(application: IMultipleAccountPublicClientApplication) {
                    b2cApp=application
                    Log.e("deeplink","init $application, b2c ${b2cApp}")
                  //  loadAccounts()
                }

                override fun onError(exception: MsalException) {
                    displayError(exception)
                    Log.d("deeplink","error msg:${exception.printStackTrace()}")

                }
            })

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
                Log.e(TAG, "onTaskCompleted: add user", )
            }

            override fun onError(exception: MsalException) {
                displayError(exception)
            }
        })
    }

    private fun displayError(exception: Exception) {
        Log.e(TAG, "displayError: ", )
    }

}