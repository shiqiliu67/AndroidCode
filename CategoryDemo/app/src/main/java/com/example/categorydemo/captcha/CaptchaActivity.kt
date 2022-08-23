package com.example.categorydemo.captcha

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.categorydemo.R
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_captcha.*


class CaptchaActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var bitmap: Bitmap
    lateinit var codeNumber: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captcha)

        createCode()
    }

    private fun createCode() {
        bitmap = Code.instance!!.createBitmap()
        iv_showCode.setImageBitmap(bitmap)
        codeNumber = Code.instance!!.code!! //captcha string
        iv_showCode.setOnClickListener {
            bitmap = Code.instance!!.createBitmap()
            iv_showCode.setImageBitmap(bitmap)
            codeNumber = Code.instance!!.code!! //captcha string
        }

        btn_send.setOnClickListener {
            val enterText = et_identify_code.text.toString()
            Log.e("abc", "createCode: enterText $enterText, code is $codeNumber ")
            // enterText.equalsIgnoreCase(codeNumber)
            if (enterText.equals(codeNumber, ignoreCase = true)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
                showDialog()
            }
        }
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_captcha)
        dialog.setCancelable(false)
        dialog.show()
        val btn = dialog.findViewById<Button>(R.id.btn_ok)
        btn.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onClick(p0: View?)  {
    }
}