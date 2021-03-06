package com.kotlinapp.swiggyclone.smoothieKotlin.repository

import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.cart.view.model.AddCartBody
import com.kotlinapp.swiggyclone.cart.view.model.GetCartResponseModel
import com.kotlinapp.swiggyclone.cart.view.model.PlaceOrderBody
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService
import com.kotlinapp.swiggyclone.userAccount.model.AddressInputBody
import retrofit2.Call

class AppRepository {
    // MAKE SURE THE API'S THAT YOU ARE CALLING HERE SHOULD RETURN RESPONSE<MODEL_CLASS> instead of call FROM THE API_INTERFACE
    suspend fun loginUserCoroutine(loginInputBody: LoginInputBody) = RetrofitService().apiInterface.loginApi(loginInputBody)

    suspend fun homeapiCoroutinesCall(accessToken:String) = RetrofitService().apiInterface.getHomeFeedResponseCoroutine(accessToken)

    suspend fun getProfileCoroutines(accessToken: String, userId:Int) = RetrofitService().apiInterface.getUserDetailsById(accessToken, userId)

    suspend fun getRestaurantDetails(accessToken: String,restaurantId:Int) = RetrofitService().apiInterface.getRestaurantDetail(accessToken, restaurantId)

    suspend fun getAllProducts(accessToken: String) = RetrofitService().apiInterface.getAllProducts(accessToken)

    suspend fun getAllProductsType(accessToken: String) = RetrofitService().apiInterface.getProductTypes(accessToken)



    suspend fun addToCart(accessToken: String,userId: Int,restaurantId: Int,productId:Int):Call<CommonStatusMessageResponse>{

        var addCartBody = AddCartBody()
        addCartBody.productId = productId
        addCartBody.userId = userId
        addCartBody.restaurantId = restaurantId
       return RetrofitService().apiInterface.addToCart(accessToken,addCartBody)
    }
    suspend fun saveUserAddress(accessToken: String,addressInputBody: AddressInputBody) = RetrofitService().apiInterface.saveUserAddress(accessToken, addressInputBody)

    suspend fun getCart(accessToken: String,userId: Int) = RetrofitService().apiInterface.getCart(accessToken, userId)
    suspend fun getUserAddress(accessToken: String,userId: String) = RetrofitService().apiInterface.getAddressUserById(accessToken,userId)

    suspend fun placeOrder(accessToken:String , placeOrderBody: PlaceOrderBody) = RetrofitService().apiInterface.placeOrder(accessToken, placeOrderBody)


}