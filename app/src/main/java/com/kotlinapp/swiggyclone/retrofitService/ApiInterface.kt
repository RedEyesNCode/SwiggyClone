package com.kotlinapp.swiggyclone.retrofitService

import android.database.Observable
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.base.StatusCodeModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {


    @GET("testapi")
    fun testApi():Call<StatusCodeModel>

    @Headers("Content-Type: application/json")
    @POST("authJWT")
    fun loginApi(@Body loginInputBody: LoginInputBody):Call<LoginDataClass>;




}