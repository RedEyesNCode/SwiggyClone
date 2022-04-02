package com.kotlinapp.swiggyclone.auth.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.auth.repository.LoginRepository
import com.kotlinapp.swiggyclone.base.StatusCodeModel
import com.kotlinapp.swiggyclone.callbacks.LoginListener

class LoginViewModel:ViewModel() {

    var verifyOtpLiveData: MutableLiveData<StatusCodeModel>?=null
    var loginDataClassLiveData : MutableLiveData<LoginDataClass>?=null
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




    fun testApi(context: Context):MutableLiveData<StatusCodeModel>{
        verifyOtpLiveData = LoginRepository().sendOtp(context)
        return verifyOtpLiveData as MutableLiveData<StatusCodeModel>


    }

    fun getLoginResponseLiveData():LiveData<LoginDataClass>{
        if(loginDataClassLiveData==null){
            loginDataClassLiveData = MutableLiveData<LoginDataClass>()
        }

        return loginDataClassLiveData as MutableLiveData<LoginDataClass>

    }

    fun loginApiCall(context: Context,loginInputBody: LoginInputBody):MutableLiveData<LoginDataClass>{
        //login repository will be intialzed by the init block
        loginDataClassLiveData = loginRepository!!.callLoginApi(context, loginInputBody,loginListener)
        return loginDataClassLiveData as MutableLiveData<LoginDataClass>
    }


    //This method is used to overide an interface in kotlin and pass that variable as a parameter in  funtion of kotlin
    var loginListener = object : LoginListener {

        override fun onSuccessListener(loginDataClass: LoginDataClass) {
            loginDataClass.Token?.let { Log.i("RETROFIT", it) }
            loginDataClassLiveData!!.postValue(loginDataClass)
        }

        override fun onError(error: String) {
/*
            Toast.makeText(context,error,Toast.LENGTH_LONG).show()
*/
        }
    }





}