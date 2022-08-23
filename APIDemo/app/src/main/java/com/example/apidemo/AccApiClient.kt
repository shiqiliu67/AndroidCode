package com.example.apidemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AccApiClient {

    fun getRetrofit(): Retrofit {
        // 12.22.2021 Problems with import of OkHttpClient.
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl("https://fabric-api.touchcast.com")//api
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    }


    fun getApiService() = getRetrofit().create(AccApiService::class.java)



}