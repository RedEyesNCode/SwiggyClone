package com.kotlinapp.swiggyclone.smoothieKotlin.repository

import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService

class AppRepository {
    suspend fun loginUserCoroutine(loginInputBody: LoginInputBody) = RetrofitService().apiInterface.loginApi(loginInputBody)

}