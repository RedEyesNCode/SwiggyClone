package com.kotlinapp.swiggyclone.userAccount.accountRepository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.kotlinapp.swiggyclone.retrofitService.ApiInterface
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService
import com.kotlinapp.swiggyclone.userAccount.model.AddressResponseData
import com.kotlinapp.swiggyclone.userAccount.model.UserDetailResponse
import com.kotlinapp.swiggyclone.utils.AppUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository {

    var addressResponseDataMutableLiveData: MutableLiveData<AddressResponseData> = MutableLiveData<AddressResponseData>()
    var userDetailsMutableLiveData:MutableLiveData<UserDetailResponse> = MutableLiveData<UserDetailResponse>()
    var apiInterface : ApiInterface ?= null

    init {
        apiInterface = RetrofitService().apiInterface
    }

    //DEFINING ALL THE API CALLS RELATED TO ADDRESS HERE

    //Calling the api to get the user details by the id of the user.
    fun getUserDetailsById(accessToken: String,userId: Int):MutableLiveData<UserDetailResponse>{
        var call = apiInterface!!.getUserDetailsById(accessToken, userId)
        call.enqueue(object :Callback<UserDetailResponse>{
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                if(response.isSuccessful && response.code()==200){
                    if(response.body()!!.status!!.contains("success")){
                        userDetailsMutableLiveData.postValue(response.body())
                    }

                }

            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {

                Log.i("ALLTHISFOR4HEARTS",t.message.toString())
                userDetailsMutableLiveData.postValue(null)

            }
        })

        return userDetailsMutableLiveData
    }

    //Calling the Api to get User Addresses by the Id of the user.
    fun callGetAddressUserByID(accessToken:String, userId:Int):MutableLiveData<AddressResponseData>{
        var  call = apiInterface!!.getAddressUserById(accessToken, userId)
        call.enqueue(object :Callback<AddressResponseData>{
            override fun onResponse(
                call: Call<AddressResponseData>,
                response: Response<AddressResponseData>
            ) {
                if(response.isSuccessful && response.code()==200){
                    if(response.body()!!.status!!.contains("success")){
                        addressResponseDataMutableLiveData.postValue(response.body())
                    }


                }


            }

            override fun onFailure(call: Call<AddressResponseData>, t: Throwable) {
                Log.i("ALLTHISFOR4HEARTS",t.message.toString())
                addressResponseDataMutableLiveData.postValue(null)
            }
        })

        return addressResponseDataMutableLiveData





    }

}