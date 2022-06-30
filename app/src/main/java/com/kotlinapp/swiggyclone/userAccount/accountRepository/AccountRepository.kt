package com.kotlinapp.swiggyclone.userAccount.accountRepository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import com.kotlinapp.swiggyclone.callbacks.AccountListener
import com.kotlinapp.swiggyclone.retrofitService.ApiInterface
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService
import com.kotlinapp.swiggyclone.userAccount.model.AddressInputBody
import com.kotlinapp.swiggyclone.userAccount.model.AddressResponseData
import com.kotlinapp.swiggyclone.userAccount.model.PastOrderResponseData
import com.kotlinapp.swiggyclone.userAccount.model.UserDetailResponse
import com.kotlinapp.swiggyclone.utils.AppUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository {

    var addressResponseDataMutableLiveData: MutableLiveData<AddressResponseData> = MutableLiveData<AddressResponseData>()
    var userDetailsMutableLiveData:MutableLiveData<UserDetailResponse> = MutableLiveData<UserDetailResponse>()
    var pastOrderResponseMutableLiveData:MutableLiveData<PastOrderResponseData> = MutableLiveData<PastOrderResponseData>()
    var commonStatusMessageResponse:MutableLiveData<CommonStatusMessageResponse> = MutableLiveData<CommonStatusMessageResponse>()



    var apiInterface : ApiInterface ?= null

    init {
        apiInterface = RetrofitService().apiInterface
    }

    //DEFINING ALL THE API CALLS RELATED TO ADDRESS HERE


    //Calling the api to get the user past order's by the id of the user.

    fun saveUserAddress(accessToken: String,addressInputBody: AddressInputBody, listener: AccountListener){
        var call = apiInterface!!.saveUserAddress(accessToken, addressInputBody)
        call.enqueue(object:Callback<CommonStatusMessageResponse>{


            override fun onResponse(
                call: Call<CommonStatusMessageResponse>,
                response: Response<CommonStatusMessageResponse>
            ) {
                if (response.isSuccessful && response.body()?.status!!.contains("success")){

                    commonStatusMessageResponse.postValue(response.body())

                }else{
                    var serverHandling = AppUtils().getServerError(response.code(),response.errorBody(),null)
                    if (serverHandling != null) {
                        listener.onError(serverHandling)
                    }


                }

            }

            override fun onFailure(call: Call<CommonStatusMessageResponse>, t: Throwable) {
                Log.i("ALLTHISFOR4HEARTS",t.message.toString())
                listener.onError(t.message.toString())
                commonStatusMessageResponse.postValue(null)

            }
        })




    }



    fun getUserPastOrdersById(accessToken: String, userId: Int):MutableLiveData<PastOrderResponseData>{
        var call = apiInterface!!.getUserPastOrders(accessToken, userId)
        call.enqueue(object :Callback<PastOrderResponseData>{
            override fun onResponse(
                call: Call<PastOrderResponseData>,
                response: Response<PastOrderResponseData>
            ) {
                if(response.code()==200 && response.isSuccessful){

                    if(response.body()!!.status!!.contains("success")){
                        pastOrderResponseMutableLiveData.postValue(response.body())

                    }else{
                        Log.i("ALLTHISFOR4HEARTS",response.code().toString()+"ERROR")

                    }

                }


            }

            override fun onFailure(call: Call<PastOrderResponseData>, t: Throwable) {
                Log.i("ALLTHISFOR4HEARTS",t.message.toString())
                pastOrderResponseMutableLiveData.postValue(null)

            }
        })


        return pastOrderResponseMutableLiveData;
    }





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
    fun callGetAddressUserByID(accessToken:String, userId:String):MutableLiveData<AddressResponseData>{
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