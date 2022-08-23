package com.shiki.progressbartest

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog

class LoadingDialog(mActivity: Activity) {
    private var activity:Activity = mActivity
    lateinit var dialog: AlertDialog
    fun startLoadingDialog(){
      var builder = AlertDialog.Builder(activity)
        var inflater = activity.layoutInflater
          builder.setView(inflater.inflate(R.layout.custom_spinner_dialog,null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }
    fun dismissDialog(){
        dialog.dismiss()
    }

}