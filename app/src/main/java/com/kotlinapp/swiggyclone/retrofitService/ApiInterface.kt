package com.kotlinapp.swiggyclone.retrofitService

import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.base.StatusCodeModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiInterface {


    @GET("testapi")
    fun testApi():Call<StatusCodeModel>

    @GET("authJWT")
    fun loginApi(@Body loginInputBody: LoginInputBody):Call<LoginDataClass>;




}