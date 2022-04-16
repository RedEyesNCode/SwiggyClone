package com.kotlinapp.swiggyclone.retrofitService

import android.database.Observable
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.base.StatusCodeModel
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse
import com.kotlinapp.swiggyclone.userAccount.model.AddressResponseData
import com.kotlinapp.swiggyclone.userAccount.model.PastOrderResponseData
import com.kotlinapp.swiggyclone.userAccount.model.UserDetailResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @GET("testapi")
    fun testApi():Call<StatusCodeModel>

    @Headers("Content-Type: application/json")
    @POST("authJWT")
    fun loginApi(@Body loginInputBody: LoginInputBody):Call<LoginDataClass>


    //HOME FEED API
    @GET("getFeed")
    fun getHomeFeedResponse(@Header("Authorization") accessToken:String):Call<HomeResponse>


    //GET USER ADDRESS BY ID ~ API
    @GET("getUserAddressById")
    fun getAddressUserById(@Header("Authorization") accessToken: String, @Query("userId") userId:Int):Call<AddressResponseData>

    //GET USER DETAILS BY ID.
    @GET("getUser")
    fun getUserDetailsById(@Header("Authorization") accessToken: String,@Query("id") userId:Int):Call<UserDetailResponse>

    //GET THE USER PAST ORDERS BY THE USER ID
    @POST("getUserPastOrders")
    fun getUserPastOrders(@Header("Authorization") accessToken: String, @Query("userId") userId: Int):Call<PastOrderResponseData>






}