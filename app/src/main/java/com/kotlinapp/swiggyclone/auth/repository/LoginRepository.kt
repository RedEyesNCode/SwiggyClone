package com.kotlinapp.swiggyclone.auth.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlinapp.swiggyclone.base.StatusCodeModel
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    var statusCodeMutableLiveData = MutableLiveData<StatusCodeModel>()

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