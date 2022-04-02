package com.kotlinapp.swiggyclone.homeScreen.homeRepository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse
import com.kotlinapp.swiggyclone.retrofitService.ApiInterface
import com.kotlinapp.swiggyclone.retrofitService.RetrofitService
import com.kotlinapp.swiggyclone.utils.AppUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRespository {


    //In Repository we define all the Mutable Live Data Parameters+ API INTERFACE OBJECT INIT
    // the Methods we are going to use in the Viewmodel
    // Don't define the Listener interface here.
    var homeResponseMutableLiveData:MutableLiveData<HomeResponse> = MutableLiveData<HomeResponse>()
    var apiInterface:ApiInterface ?=null
    init {

        //You can create Multiple Retrofit().apiInterface methods to according to
        //the different urls that you are going to use.
        apiInterface = RetrofitService().apiInterface
    }


    //Defining the retrofit function here.
    fun callHomeApi(context: Context, accessToken:String):MutableLiveData<HomeResponse>{
        var call :Call<HomeResponse>
        call = apiInterface!!.getHomeFeedResponse(accessToken)
        call.enqueue(object : Callback<HomeResponse>{
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {

                if(response.code()==200){
                    homeResponseMutableLiveData.postValue(response.body())
                }else{
                    var serverHandling: String? = AppUtils().getServerError(response.code(),response.errorBody(),context)
                    if (serverHandling != null) {
                        Toast.makeText(context,serverHandling,Toast.LENGTH_SHORT).show()
                    }
                }


            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Toast.makeText(context,t.message,Toast.LENGTH_SHORT).show()

                homeResponseMutableLiveData.postValue(null)
            }
        })


        return homeResponseMutableLiveData
    }



}