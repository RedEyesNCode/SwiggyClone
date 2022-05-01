package com.kotlinapp.swiggyclone.retrofitService

import com.kotlinapp.swiggyclone.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val  serverUrl = "http://3.111.128.169:9999/swiggy/"


    //Step 2
    //Creating a Value of type Retrofit Builder and Setting levelType Interceptor and Building it in the apiInterface val.
    val retrofitClient: Retrofit.Builder by lazy {
        val levelType: HttpLoggingInterceptor.Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = HttpLoggingInterceptor.Level.BODY else levelType = HttpLoggingInterceptor.Level.NONE
        val loggingInterceptor= HttpLoggingInterceptor()
        loggingInterceptor.setLevel(levelType)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)
        Retrofit.Builder().baseUrl(serverUrl).client(okHttpClient.build()).addConverterFactory(
            GsonConverterFactory.create())
    }

    //Step 3

    val apiInterface:ApiInterface by lazy {
        retrofitClient.build().create(ApiInterface::class.java)
    }

}