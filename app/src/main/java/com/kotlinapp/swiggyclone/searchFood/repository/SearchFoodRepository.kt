package com.kotlinapp.swiggyclone.searchFood.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlinapp.swiggyclone.homeScreen.models.AllRestaurantsResponseData
import com.kotlinapp.swiggyclone.retrofitService.ApiInterface
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService
import com.kotlinapp.swiggyclone.utils.AppUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFoodRepository {

    var allRestaurantsResponseDataMutableLiveData:MutableLiveData<AllRestaurantsResponseData> = MutableLiveData<AllRestaurantsResponseData>()
    var apiInterface:ApiInterface ?=null

    init {
        apiInterface = RetrofitService().apiInterface
    }


    //Calling the get all restaurants list api for the search food fragment screen.
    fun getAllRestaurants(accessToken:String,context: Context):MutableLiveData<AllRestaurantsResponseData>{
        var call: Call<AllRestaurantsResponseData>
        call = apiInterface!!.getRestaurantsList(accessToken)
        call.enqueue(object :Callback<AllRestaurantsResponseData>{
            override fun onResponse(
                call: Call<AllRestaurantsResponseData>,
                response: Response<AllRestaurantsResponseData>
            ) {
                if(response.code()==200 && response.isSuccessful){
                    if(response.body()!!.status!!.contains("success")){
                        allRestaurantsResponseDataMutableLiveData.postValue(response.body())
                    }
                }else{
                    var serverHandling = AppUtils().getServerError(response.code(),response.errorBody(),context)
                    if (serverHandling != null) {
                        Log.i("ALLTHISFOR4HEARTS",serverHandling)
                    }
                }



            }

            override fun onFailure(call: Call<AllRestaurantsResponseData>, t: Throwable) {

                Log.i("ALLTHISFOR4HEARTS", "onFailure: "+t.message)
            }


        })

        return allRestaurantsResponseDataMutableLiveData
    }





}