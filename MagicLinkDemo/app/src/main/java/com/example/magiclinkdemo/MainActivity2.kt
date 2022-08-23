package com.example.magiclinkdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        clickEvent()
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ", )
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ", )

    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ", )
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ", )
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        setContentView(R.layout.activity_main2)
        clickEvent()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = intent?.data
        Log.e(TAG, "onNewIntent: intent is $uri", )
        uri?.let {
            val intent = Intent(this, B2CActivity::class.java)
            intent.data = uri
            startActivity(intent)
        }
//        val intent = Intent(this, B2CActivity::class.java)
//        intent.data = uri
//        startActivity(intent)
    }

    private fun clickEvent() {
        //general
        btn_general.setOnClickListener {
            Intent(this, B2CActivity::class.java).apply {
                putExtra("b2c", 2)
                startActivity(this)
            }
        }
        //edit profile
        btn_edit.setOnClickListener {
            val intent = Intent(this, B2CActivity::class.java)
            intent.putExtra("b2c", 1)
            startActivity(intent)
        }
        //logout
        btn_logout.setOnClickListener {
            //b2c logout
            val sharedPreferences =
                getSharedPreferences("current_user", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            val intent = Intent(this, B2CActivity::class.java)
            intent.putExtra("b2c", 3)
            startActivity(intent)
            //  B2CActivity().b2cLogoutFlow()
        }
        //forget password
        //
    }
    companion object{
        val TAG = "MainActivity2"
    }
}