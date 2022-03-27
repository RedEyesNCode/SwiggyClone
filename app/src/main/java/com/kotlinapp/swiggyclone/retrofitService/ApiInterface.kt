package com.kotlinapp.swiggyclone.retrofitService

import com.kotlinapp.swiggyclone.base.StatusCodeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {


    @GET("testapi")
    fun testApi():Call<StatusCodeModel>




}