package com.kotlinapp.swiggyclone.callbacks

import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import java.lang.Error

interface AccountListener {

    fun onSuccessSaveAddress(commonStatusMessageResponse: CommonStatusMessageResponse)
    fun onError(error: String)

}