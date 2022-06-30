package com.kotlinapp.swiggyclone.userAccount.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import com.kotlinapp.swiggyclone.callbacks.AccountListener
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.userAccount.accountRepository.AccountRepository
import com.kotlinapp.swiggyclone.userAccount.model.AddressInputBody
import com.kotlinapp.swiggyclone.userAccount.model.AddressResponseData
import com.kotlinapp.swiggyclone.userAccount.model.PastOrderResponseData
import com.kotlinapp.swiggyclone.userAccount.model.UserDetailResponse
import com.kotlinapp.swiggyclone.utils.AppUtils
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.io.IOException

class AccountViewModel(var app:Application,var appRepository: AppRepository):AndroidViewModel(app) {


    //OBSERVE THIS MUTABLE LIVE DATA IN YOUR VIEW
    var addressResponseMutableLiveData:MutableLiveData<AddressResponseData> = MutableLiveData()
    var userDetailResponseMutableLiveData:MutableLiveData<UserDetailResponse> = MutableLiveData()
    var pastOrderResponseDataMutableLiveData : MutableLiveData<PastOrderResponseData> = MutableLiveData()
    var commonStatusMessageResponse:MutableLiveData<CommonStatusMessageResponse> = MutableLiveData()





    var isFailed: MutableLiveData<String> = MutableLiveData<String>()
    var isConnecting: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun getIsFailed(): LiveData<String> {
        return isFailed
    }
    fun getIsConnecting(): LiveData<Boolean> {
        return isConnecting
    }

    //Calling all the api's defined in the repository here/
    //Also observe each of the api response here.

    var accountRepository:AccountRepository ?=null
    init {
        accountRepository = AccountRepository()

    }

    fun saveUserAddress(accessToken: String,addressInputBody: AddressInputBody)= viewModelScope.launch {
        saveUserAddressSuspended(accessToken,addressInputBody)
    }
    fun getUserAddress(accessToken: String,userId: String)=viewModelScope.launch {
        getUserAddressSuspended(accessToken,userId)
    }

    suspend fun getUserAddressSuspended(accessToken: String, userId: String) {
        isConnecting.value = true
        try {
            var appRepository = AppRepository()
            var response = appRepository.getUserAddress(accessToken, userId).awaitResponse()
            addressResponseMutableLiveData.postValue(response.body())
        }catch (e:Throwable){
            when(e){
                is IOException->{
                    isFailed.value = "IO EXCEPTION PLEASE TRY AGAIN"
                    AppUtils().showLog("IO EXCEPTION PLEASE TRY AGAIN")
                }
                is Exception->{
                    isFailed.value = "EXCEPTION OCCURED"
                    AppUtils().showLog("EXCEPTION OCCURED !")

                }



            }

        }
    }


    suspend fun saveUserAddressSuspended(accessToken: String, addressInputBody: AddressInputBody) {
        isConnecting.value = true
        try {
            var appRepository = AppRepository()
            var response = appRepository.saveUserAddress(accessToken, addressInputBody).awaitResponse()
            commonStatusMessageResponse.postValue(response.body())
        }catch (e:Throwable){
            when(e){
                is IOException->{
                    isFailed.value = "IO EXCEPTION PLEASE TRY AGAIN"
                    AppUtils().showLog("IO EXCEPTION PLEASE TRY AGAIN")
                }
                is Exception->{
                    isFailed.value = "EXCEPTION OCCURED"
                    AppUtils().showLog("EXCEPTION OCCURED !")

                }



            }

        }

    }


    //CALLING THE  GET USER PAST ORDER'S BY THE ID OF THE USER.
    fun getUserDetailsCoroutines(accessToken: String,userId: Int) = viewModelScope.launch {
        getUserdetailsSuspended(accessToken,userId)

    }
    suspend fun getUserdetailsSuspended(accessToken: String,userId: Int){

        isConnecting.value = true

        try {
            var appRepository = AppRepository()

            var response = appRepository.getProfileCoroutines(accessToken, userId).awaitResponse()
            userDetailResponseMutableLiveData.postValue(response.body())
        }catch (e:Throwable){
            when(e){
                is IOException -> {
                    isFailed.value = "IO EXCEPTION PLEASE TRY AGAIN"
                    AppUtils().showLog("IO EXCEPTION PLEASE TRY AGAIN")
                }
                is Exception -> {
                    isFailed.value = "EXCEPTION OCCURED"
                    AppUtils().showLog("EXCEPTION OCCURED !")
                }
            }
        }
    }


    fun getUserPastOrderById(accessToken: String, userId: Int):MutableLiveData<PastOrderResponseData>{

        pastOrderResponseDataMutableLiveData = accountRepository!!.getUserPastOrdersById(accessToken, userId)
        return  pastOrderResponseDataMutableLiveData

    }


    //CALLING THE GET USER DETAILS BY ID API FROM THE VIEWMODEL.
    fun getUserDetailsById(accessToken:String, userId: Int):MutableLiveData<UserDetailResponse>{
        userDetailResponseMutableLiveData = accountRepository!!.getUserDetailsById(accessToken, userId)
        return userDetailResponseMutableLiveData


    }



    //CALLING THE GET ADDRESSES API FROM THE VIEW MODEL
    fun getAddressUserById(accessToken : String, userId:String):MutableLiveData<AddressResponseData>{
        addressResponseMutableLiveData = accountRepository!!.callGetAddressUserByID(accessToken, userId)
        return addressResponseMutableLiveData

    }





}