package com.kotlinapp.swiggyclone.callbacks

import com.kotlinapp.swiggyclone.auth.model.LoginDataClass

interface LoginListener {
    fun onSucessListener(loginDataClass: LoginDataClass)
    fun onError(error:String)

}