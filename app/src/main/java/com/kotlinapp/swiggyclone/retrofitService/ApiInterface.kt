package com.kotlinapp.swiggyclone.retrofitService

import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.base.StatusCodeMessageModel
import com.kotlinapp.swiggyclone.homeScreen.models.AllRestaurantsResponseData
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse
import com.kotlinapp.swiggyclone.restaurantDetail.GetWishlistResponseData
import com.kotlinapp.swiggyclone.userAccount.model.AddressResponseData
import com.kotlinapp.swiggyclone.userAccount.model.PastOrderResponseData
import com.kotlinapp.swiggyclone.userAccount.model.UserDetailResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @GET("testapi")
    fun testApi():Call<StatusCodeMessageModel>

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

    @GET("getRestaurantlist")
    fun getRestaurantsList(@Header("Authorization") accessToken: String):Call<AllRestaurantsResponseData>

    @POST("addtoWislist")
    fun addToWishList(@Header("Authorization") accessToken: String, @Query("restaurantId") restaurantId:Int, @Query("userId") userId:Int):Call<StatusCodeMessageModel>


    @GET("getWishList")
    fun getUserWishlist(@Header("Authorization") accessToken: String, @Query("id") userId: Int):Call<GetWishlistResponseData>







}