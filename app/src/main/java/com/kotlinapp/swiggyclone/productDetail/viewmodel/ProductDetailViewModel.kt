package com.kotlinapp.swiggyclone.productDetail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlinapp.swiggyclone.productDetail.model.AllProductsResponseModel
import com.kotlinapp.swiggyclone.productDetail.model.RestaurantDetailResponse
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.io.IOException

class ProductDetailViewModel(var app:Application):AndroidViewModel(app) {

    var restaurantDetailResponseMutableLiveData : MutableLiveData<RestaurantDetailResponse> = MutableLiveData()
    var allProductsResponseModelMutableLiveData : MutableLiveData<AllProductsResponseModel> = MutableLiveData()



    var isFailed: MutableLiveData<String> = MutableLiveData<String>()
    var isConnecting: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    lateinit var appRepository :AppRepository

    init {
        appRepository = AppRepository()
    }
    fun getIsFailed(): LiveData<String> {
        return isFailed
    }
    fun getIsConnecting(): LiveData<Boolean> {
        return isConnecting
    }

    fun getRestaurantDetails(accessToken:String, restaurantID:Int) = viewModelScope.launch {
        getRestaurantSuspended(accessToken,restaurantID)
    }

    fun getAllProducts(accessToken: String) = viewModelScope.launch {

        getAllProductsSuspended(accessToken)


    }

    suspend fun getAllProductsSuspended(accessToken: String) {
        isConnecting.value= true
        try {
            var response= appRepository.getAllProducts(accessToken).awaitResponse()
            if(response.isSuccessful){
                allProductsResponseModelMutableLiveData.postValue(response.body())
            }else{
                isFailed.value = "RESPONSE CODE IS " + response.code()
            }

        }catch (t:Throwable){
            when(t){
                is IOException -> {
                    isFailed.value = "IO Exception Please try again"

                }
                is Exception -> {
                    isFailed.value = "Exception occured ! "+t.message

                }
            }

        }


    }


    suspend fun getRestaurantSuspended(accessToken: String, restaurantID: Int) {
        isConnecting.value = true
        try {
            var response = appRepository.getRestaurantDetails(accessToken,restaurantID).awaitResponse()
            if(response.isSuccessful){
                restaurantDetailResponseMutableLiveData.postValue(response.body())

            }else{
                isFailed.value = "RESPONSE CODE IS " + response.code()
            }
        }catch (t:Throwable){
            when(t){
                is IOException -> {
                    isFailed.value = "IO Exception Please try again"
                }
                is Exception -> {
                    isFailed.value = "Exception occured ! "+t.message
                }
            }
        }
    }
}