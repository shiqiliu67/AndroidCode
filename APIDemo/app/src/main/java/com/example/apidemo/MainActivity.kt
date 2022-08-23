package com.example.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject

class MainActivity : AppCompatActivity() {
    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testApi()
    }
    fun testApi(){
        val jsonMsg = JsonObject()
        jsonMsg.addProperty("organization_id", "7")
        jsonMsg.addProperty("group_path", "accenture-main-vwAHFqWD")
        jsonMsg.addProperty("data_container", "accenture_public")
        jsonMsg.addProperty("query", "Artificial Intelligence")
        jsonMsg.addProperty("offset", "0")
        jsonMsg.addProperty("limit", "20")
        Log.e(TAG, jsonMsg.toString())
        //val gson = Gson().fromJson()
        //viewmodel
        viewModel.successfulResponse.observe(this){
            Log.e(TAG, "testApi: successful! response is $it")
        }
        viewModel.failedResponse.observe(this){
            Log.e(TAG, "testApi: Failed! response is $it")
        }
        viewModel.getApiData(jsonMsg.toString())
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}