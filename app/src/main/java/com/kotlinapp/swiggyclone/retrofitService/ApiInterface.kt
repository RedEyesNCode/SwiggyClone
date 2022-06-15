package com.kotlinapp.swiggyclone.retrofitService

import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.base.StatusCodeMessageModel
import com.kotlinapp.swiggyclone.cart.view.model.AddCartBody
import com.kotlinapp.swiggyclone.cart.view.model.GetCartResponseModel
import com.kotlinapp.swiggyclone.homeScreen.models.AllRestaurantsResponseData
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse
import com.kotlinapp.swiggyclone.productDetail.model.AllProductsResponseModel
import com.kotlinapp.swiggyclone.productDetail.model.ProductTypeResponse
import com.kotlinapp.swiggyclone.productDetail.model.RestaurantDetailResponse
import com.kotlinapp.swiggyclone.restaurantDetail.GetWishlistResponseData
import com.kotlinapp.swiggyclone.userAccount.model.AddressInputBody
import com.kotlinapp.swiggyclone.userAccount.model.AddressResponseData
import com.kotlinapp.swiggyclone.userAccount.model.PastOrderResponseData
import com.kotlinapp.swiggyclone.userAccount.model.UserDetailResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @GET("testapi")
    fun testApi():Call<StatusCodeMessageModel>

    // NEED TO TAKE CALL HERE TO INTIALZE THE CALL ADAPTER FOR THE RETROFIT.
    @Headers("Content-Type: application/json")
    @POST("authJWT")
    fun loginApi(@Body loginInputBody: LoginInputBody):Call<LoginDataClass>

    @Headers("Content-Type: application/json")
    @POST("authJWT")
    fun loginapiCoroutines(@Body loginInputBody: LoginInputBody):Response<LoginDataClass>



    //HOME FEED API
    @GET("getFeed")
    fun getHomeFeedResponse(@Header("Authorization") accessToken:String):Call<HomeResponse>

    //HOME FEED API
    @GET("getFeed")
    fun getHomeFeedResponseCoroutine(@Header("Authorization") accessToken:String):Call<HomeResponse>

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


    @POST("saveUserAddress")
    fun saveUserAddress(@Header("Authorization") accessToken: String , @Body addressInputBody: AddressInputBody)


    // GET DETAILS OF THE RESTAURANT BY THE ID
    @POST("getRestaurantDetail")
    fun getRestaurantDetail(@Header("Authorization") accessToken: String,@Query("restaurantId")restaurantId: Int):Call<RestaurantDetailResponse>


    // GET ALL THE PRODUCT LISTINGS

    @GET("getAllProducts")
    fun getAllProducts(@Header("Authorization") accessToken :String):Call<AllProductsResponseModel>

    @GET("getProductTypes")
    fun getProductTypes(@Header("Authorization") accessToken: String):Call<List<ProductTypeResponse>>

    @POST("addtoCart")
    fun addToCart(@Header("Authorization") accessToken: String,@Body addCartBody: AddCartBody):Call<CommonStatusMessageResponse>


    @GET("getCart")
    fun getCart(@Header("Authorization") accessToken: String,@Query("userId") userId: Int):Call<GetCartResponseModel>





}