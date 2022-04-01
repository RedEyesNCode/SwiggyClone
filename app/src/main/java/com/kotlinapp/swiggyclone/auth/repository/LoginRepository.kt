package com.kotlinapp.swiggyclone.auth.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.base.StatusCodeModel
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    var statusCodeMutableLiveData = MutableLiveData<StatusCodeModel>()
    var loginDataClass = MutableLiveData<LoginDataClass>()



    //Calling the login api and get Access Token from the Response

    fun callLoginApi(context: Context,loginInputBody: LoginInputBody):MutableLiveData<LoginDataClass>{
        var call:Call<LoginDataClass>
        call = RetrofitService().apiInterface.loginApi(loginInputBody)
        call.enqueue(object :Callback<LoginDataClass>{

            override fun onResponse(
                call: Call<LoginDataClass>,
                response: Response<LoginDataClass>
            ) {

                if(response.code()==200){

                }else{
                    var serverHandling:String =
                }
            }

            override fun onFailure(call: Call<LoginDataClass>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })





    }

    //Calling the Api to Send the Otp to Required Number.
    fun sendOtp(context: Context): MutableLiveData<StatusCodeModel>{
        var call: Call<StatusCodeModel>
        call = RetrofitService().apiInterface.testApi()
        call.enqueue(object : Callback<StatusCodeModel> {
            override fun onResponse(call: Call<StatusCodeModel>, response: Response<StatusCodeModel>) {
                var message:String
                var data =  response.body()
                message = data!!.message.toString()

                //Dont Forget to do this Else you wont Get Any Return Data.
                //Itslike .postValue(response.body) in Java.
                statusCodeMutableLiveData.value = response.body()


            }

            override fun onFailure(call: Call<StatusCodeModel>, t: Throwable) {

                Log.i("INFO :",t.message.toString())
            }
        })
        return statusCodeMutableLiveData
    }
}