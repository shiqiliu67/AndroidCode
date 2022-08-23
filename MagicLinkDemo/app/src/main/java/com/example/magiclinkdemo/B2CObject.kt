package com.example.magiclinkdemo

import android.app.Application
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import com.microsoft.identity.client.IMultipleAccountPublicClientApplication
import com.microsoft.identity.client.IPublicClientApplication
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.client.exception.MsalException

object B2CObject {
//    var b2cApp: IMultipleAccountPublicClientApplication? = null
//    var users: LiveData<List<B2CUser>>? = null
//    //b2c interate
//    fun integrate(){
//        PublicClientApplication.createMultipleAccountPublicClientApplication(this,
//            R.raw.msal_config,
//            object : IPublicClientApplication.IMultipleAccountApplicationCreatedListener {
//                override fun onCreated(application: IMultipleAccountPublicClientApplication) {
//                    B2CObject.b2cApp = application
//                    Log.d("deeplink", "init $application, b2c ${B2CObject.b2cApp}")
//               //     loadAccounts()
//
//                }
//
//                override fun onError(exception: MsalException) {
//                 //   displayError(exception)
//                    Log.d("deeplink", "error msg:${exception.printStackTrace()}")
//                }
//            })
//    }


}
