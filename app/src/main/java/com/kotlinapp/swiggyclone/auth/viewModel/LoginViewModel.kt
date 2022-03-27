package com.kotlinapp.swiggyclone.auth.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapp.swiggyclone.auth.repository.LoginRepository
import com.kotlinapp.swiggyclone.base.StatusCodeModel

class LoginViewModel:ViewModel() {

    var verifyOtpLiveData: MutableLiveData<StatusCodeModel>?=null


    fun testApi(context: Context):MutableLiveData<StatusCodeModel>{
        verifyOtpLiveData = LoginRepository().sendOtp(context)
        return verifyOtpLiveData as MutableLiveData<StatusCodeModel>


    }


}