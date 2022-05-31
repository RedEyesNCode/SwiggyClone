package com.kotlinapp.swiggyclone.smoothieKotlin.repository

import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService

class AppRepository {
    // MAKE SURE THE API'S THAT YOU ARE CALLING HERE SHOULD RETURN RESPONSE<MODEL_CLASS> instead of call FROM THE API_INTERFACE
    suspend fun loginUserCoroutine(loginInputBody: LoginInputBody) = RetrofitService().apiInterface.loginApi(loginInputBody)

    suspend fun homeapiCoroutinesCall(accessToken:String) = RetrofitService().apiInterface.getHomeFeedResponseCoroutine(accessToken)

    suspend fun getProfileCoroutines(accessToken: String, userId:Int) = RetrofitService().apiInterface.getUserDetailsById(accessToken, userId)

}