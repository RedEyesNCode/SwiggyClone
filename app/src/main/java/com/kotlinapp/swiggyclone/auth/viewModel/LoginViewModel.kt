package com.kotlinapp.swiggyclone.auth.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.auth.repository.LoginRepository
import com.kotlinapp.swiggyclone.base.StatusCodeMessageModel
import com.kotlinapp.swiggyclone.callbacks.LoginListener

class LoginViewModel:ViewModel() {

    var verifyOtpLiveData: MutableLiveData<StatusCodeMessageModel> = MutableLiveData()
    var loginDataClassLiveData : MutableLiveData<LoginDataClass> = MutableLiveData<LoginDataClass>()
    var isFailed:LiveData<String> = MutableLiveData<String>()
    var isConnecting:LiveData<String> = MutableLiveData<String>()

    fun getIsFailed():LiveData<String>{
        return isFailed
    }
    fun getIsConnecting():LiveData<String>{
        return isConnecting
    }



    var loginRepository:LoginRepository?=null
    init {

        loginRepository = LoginRepository()
    }




    fun testApi(context: Context):MutableLiveData<StatusCodeMessageModel>{
        verifyOtpLiveData = LoginRepository().sendOtp(context)
        return verifyOtpLiveData as MutableLiveData<StatusCodeMessageModel>


    }

    fun getLoginResponseLiveData():LiveData<LoginDataClass>{
        if(loginDataClassLiveData==null){
            loginDataClassLiveData = MutableLiveData<LoginDataClass>()
        }

        return loginDataClassLiveData as MutableLiveData<LoginDataClass>

    }

    fun loginApiCall(context: Context,loginInputBody: LoginInputBody){
        //login repository will be intialzed by the init block
         var loginDataClassLiveDataResponse = loginRepository!!.callLoginApi(context, loginInputBody)
        //change in the mutable live data to attach the observers first.
        loginDataClassLiveData?.postValue(loginDataClassLiveDataResponse.value)

    }



    }


