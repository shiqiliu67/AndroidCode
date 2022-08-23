package com.example.urldemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.urldemoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //click event
        init()
    }
    private fun init(){
        binding.btnChart.setOnClickListener {
            val url = binding.editText.text.toString()//"https://accenturemobileapps.storied.co/charts-batch-1/graph-1-2-rise-of-the-forerunners"//binding.editText.text.toString()
            Log.e(TAG, "init: url is $url", )
            val intent = Intent(this,ChartActivity::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }
        binding.btnMes.setOnClickListener {
            val url = binding.editText.text
            Log.e(TAG, "init: url is $url", )
            val intent = Intent(this,MESActivity::class.java)
            intent.putExtra("url",url.toString())
            startActivity(intent)
        }
    }
    companion object{
        private val TAG = "MainActivity"
    }
}