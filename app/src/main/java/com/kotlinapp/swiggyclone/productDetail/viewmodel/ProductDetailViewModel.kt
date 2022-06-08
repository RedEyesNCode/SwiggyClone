package com.kotlinapp.swiggyclone.productDetail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import com.kotlinapp.swiggyclone.productDetail.model.AllProductsResponseModel
import com.kotlinapp.swiggyclone.productDetail.model.ProductTypeResponse
import com.kotlinapp.swiggyclone.productDetail.model.RestaurantDetailResponse
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.io.IOException

class ProductDetailViewModel(var app:Application):AndroidViewModel(app) {

    var restaurantDetailResponseMutableLiveData : MutableLiveData<RestaurantDetailResponse> = MutableLiveData()
    var allProductsResponseModelMutableLiveData : MutableLiveData<AllProductsResponseModel> = MutableLiveData()
    var productTypeResponseMutableLiveData : MutableLiveData<List<ProductTypeResponse>> = MutableLiveData()
    var commonStatusMessageResponseMutableLiveData : MutableLiveData<CommonStatusMessageResponse> = MutableLiveData()



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

    fun getAllProductTypes(accessToken: String)= viewModelScope.launch {

        getAllProductTypesSuspended(accessToken)

    }

    //Add to Cart Code.
    fun addtoCart(accessToken: String,userId:Int,restaurantID: Int,productId:Int)= viewModelScope.launch {

        addtoCartSuspended(accessToken,userId, restaurantID, productId)

    }
    suspend fun addtoCartSuspended(accessToken: String,userId:Int,restaurantID: Int,productId:Int){
        isConnecting.value= true
        try {
            var response= appRepository.addToCart(accessToken,userId,restaurantID,productId).awaitResponse()
            if(response.isSuccessful){
                commonStatusMessageResponseMutableLiveData.postValue(response.body())
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




    suspend fun getAllProductTypesSuspended(accessToken: String){
        isConnecting.value= true
        try {
            var response= appRepository.getAllProductsType(accessToken).awaitResponse()
            if(response.isSuccessful){
                productTypeResponseMutableLiveData.postValue(response.body())
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