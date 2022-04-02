package com.kotlinapp.swiggyclone.retrofitService

import android.database.Observable
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.base.StatusCodeModel
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @GET("testapi")
    fun testApi():Call<StatusCodeModel>

    @Headers("Content-Type: application/json")
    @POST("authJWT")
    fun loginApi(@Body loginInputBody: LoginInputBody):Call<LoginDataClass>


    @GET("getFeed")
    fun getHomeFeedResponse(@Header("Authorization") accessToken:String):Call<HomeResponse>




}