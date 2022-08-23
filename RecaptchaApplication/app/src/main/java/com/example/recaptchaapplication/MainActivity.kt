package com.example.recaptchaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.safetynet.SafetyNet

class MainActivity : AppCompatActivity() {
    var userName : String = ""
    var password :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userName = findViewById<EditText>(R.id.username).text.toString()
        password = findViewById<EditText>(R.id.password).text.toString()
        var loginBtn = findViewById<Button>(R.id.login_btn)
       // processLogin()
        loginBtn.setOnClickListener {
           processLogin()
        }


    }

    fun processLogin(){
        SafetyNet.getClient(this).verifyWithRecaptcha("6LeAkp0dAAAAAPDuesxBgyBgGqPYz73JaZ_XbJX6")
            .addOnSuccessListener {
                val captchaToken = it.tokenResult
                if (!captchaToken.isEmpty()){
                    processLoginStep(captchaToken,userName,password)
                }
                else{
                    Toast.makeText(this,"Invalid Captcha Response",Toast.LENGTH_LONG).show()
                }


            }
            .addOnFailureListener{
                Toast.makeText(this,"Failed to load captcha",Toast.LENGTH_LONG).show()
            }
    }
    fun processLoginStep(token:String,userName:String,password:String){
        Log.d(TAG, "processLoginStep: token is $token, userName : $userName, password : $password")
    }

    companion object{
        val TAG = "Captcha"
    }
}