package com.example.customnotificationpermissiondemo

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        // NotificationManagerCompat.from(this).areNotificationsEnabled() check the notification is enable or not
        if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            Log.e(TAG, "onResume:")
        } else {
            showNotificationDialog()
            //  goToNotificationSetting(context = this)
        }
    }

    private fun showNotificationDialog() {
        notificationDialog = Dialog(this)
        notificationDialog.setContentView(R.layout.dialog_custom_dialog)
        //notificationDialog.setCancelable(false)
        notificationDialog.setCanceledOnTouchOutside(false)
        notificationDialog.window!!.setGravity(Gravity.CENTER)
        notificationDialog.getWindow()!!
            .setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        Log.e(TAG, "showDialog: show")
        notificationDialog.show()
        val btnY = notificationDialog.findViewById<TextView>(R.id.dialog_yes)
        val btnN = notificationDialog.findViewById<TextView>(R.id.dialog_no)
        btnY.setOnClickListener {
            notificationDialog.dismiss()
        }
        btnN.setOnClickListener {
            notificationDialog.dismiss()
            goToNotificationSetting(context = this)
        }
    }

    companion object {
        lateinit var notificationDialog: Dialog
        const val TAG = "MainActivity"
        fun goToNotificationSetting(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Android 8.0 and above
                try {
                    Intent().apply {
                        action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        putExtra(Settings.EXTRA_CHANNEL_ID, context.applicationInfo.uid)
                        context.startActivity(this)
                    }
                } catch (e: Exception) {
                    toPermissionSetting(context)
                }
            }
        }

        private fun toPermissionSetting(activity: Context) {
            try {
                toApplicationInfo(activity)
            } catch (e: Exception) {
                toSystemConfig(activity)
            }
        }

        /**
         * application
         *
         * @param activity
         */
        private fun toApplicationInfo(activity: Context) {
            try {
                val localIntent = Intent()
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                localIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                localIntent.data = Uri.fromParts("package", activity.packageName, null)
                activity.startActivity(localIntent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /**
         * system setting
         * @param activity
         */
        private fun toSystemConfig(activity: Context) {
            Log.e(TAG, "toSystemConfig: ")
            try {
                val intent = Intent(Settings.ACTION_SETTINGS)
                activity.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}