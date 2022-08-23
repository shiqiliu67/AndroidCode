package com.example.apidemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import okhttp3.ResponseBody

class MainViewModel : ViewModel() {
    val repository = AccDataProvider()
    val successfulResponse =MutableLiveData<AccSearchResponse>()
    val failedResponse = MutableLiveData<String>()
    //val body = RequestBody.create("organization_id",7)

    fun getApiData(param:String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = repository.getApiData(param = param)
                if (response.isSuccessful){
                    successfulResponse.postValue(response.body())
                }
                else{

                    failedResponse.postValue("Failed! ${response.errorBody()}")
                }
            }
            catch (e:Exception) {
                e.printStackTrace()
                failedResponse.postValue("failed, Error is $e")
            }
        }
    }
}