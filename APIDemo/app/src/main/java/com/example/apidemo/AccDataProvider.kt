package com.example.apidemo

import okhttp3.RequestBody

class AccDataProvider {
    suspend fun getApiData(param:String) = AccApiClient.getApiService().getData(body = param)

}