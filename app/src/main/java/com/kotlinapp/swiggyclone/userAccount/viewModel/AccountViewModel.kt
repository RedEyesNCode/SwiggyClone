package com.kotlinapp.swiggyclone.userAccount.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapp.swiggyclone.userAccount.accountRepository.AccountRepository
import com.kotlinapp.swiggyclone.userAccount.model.AddressResponseData
import com.kotlinapp.swiggyclone.userAccount.model.PastOrderResponseData
import com.kotlinapp.swiggyclone.userAccount.model.UserDetailResponse

class AccountViewModel:ViewModel() {


    //OBSERVE THIS MUTABLE LIVE DATA IN YOUR VIEW
    var addressResponseMutableLiveData:MutableLiveData<AddressResponseData> = MutableLiveData()
    var userDetailResponseMutableLiveData:MutableLiveData<UserDetailResponse> = MutableLiveData()
    var pastOrderResponseDataMutableLiveData : MutableLiveData<PastOrderResponseData> = MutableLiveData()



    var isFailed: LiveData<String> = MutableLiveData<String>()
    var isConnecting: LiveData<String> = MutableLiveData<String>()

    fun getIsFailed(): LiveData<String> {
        return isFailed
    }
    fun getIsConnecting(): LiveData<String> {
        return isConnecting
    }

    //Calling all the api's defined in the repository here/
    //Also observe each of the api response here.

    var accountRepository:AccountRepository ?=null
    init {
        accountRepository = AccountRepository()

    }

    //CALLING THE  GET USER PAST ORDER'S BY THE ID OF THE USER.
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
    fun getAddressUserById(accessToken : String, userId:Int):MutableLiveData<AddressResponseData>{
        addressResponseMutableLiveData = accountRepository!!.callGetAddressUserByID(accessToken, userId)
        return addressResponseMutableLiveData

    }





}