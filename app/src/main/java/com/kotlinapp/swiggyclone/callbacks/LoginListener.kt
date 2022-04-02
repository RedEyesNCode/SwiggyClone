package com.kotlinapp.swiggyclone.callbacks

import com.kotlinapp.swiggyclone.auth.model.LoginDataClass

interface LoginListener {
    fun onSuccessListener(loginDataClass: LoginDataClass)
    fun onError(error:String)

}