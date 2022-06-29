package com.kotlinapp.swiggyclone.cart.view.cartViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import com.kotlinapp.swiggyclone.cart.view.model.GetCartResponseModel
import com.kotlinapp.swiggyclone.cart.view.model.PlaceOrderBody
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.io.IOException

class CartViewModel(var app: Application): AndroidViewModel(app)  {

    var getCartMutableLiveData:MutableLiveData<GetCartResponseModel> = MutableLiveData()
    var commonStatusMessageResponse:MutableLiveData<CommonStatusMessageResponse> = MutableLiveData()

    var isFailed: MutableLiveData<String> = MutableLiveData<String>()
    var isConnecting: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    lateinit var appRepository : AppRepository

    init {
        appRepository = AppRepository()
    }
    fun getIsFailed(): LiveData<String> {
        return isFailed
    }
    fun getIsConnecting(): LiveData<Boolean> {
        return isConnecting
    }

    fun placeOrder(accessToken: String,placeOrderBody: PlaceOrderBody) = viewModelScope.launch {
        placeOrderSuspended(accessToken,placeOrderBody)

    }
    suspend fun placeOrderSuspended(accessToken: String,placeOrderBody: PlaceOrderBody){
        isConnecting.value = true

        try {
            var response = appRepository.placeOrder(accessToken, placeOrderBody).awaitResponse()
            if(response.isSuccessful){
                commonStatusMessageResponse.postValue(response.body())
            }else{
                isFailed.value = "RESPONSE CODE IS :: " +response.code()
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

    fun getUserCart(accessToken:String, userId: Int) = viewModelScope.launch {
        getUserCartSuspended(accessToken,userId)
    }
    suspend fun getUserCartSuspended(accessToken: String,userId:Int){
        isConnecting.value = true
        try {
            var response= appRepository.getCart(accessToken,userId).awaitResponse()
            if(response.isSuccessful){
                getCartMutableLiveData.postValue(response.body())
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