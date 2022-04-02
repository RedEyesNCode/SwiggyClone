package com.kotlinapp.swiggyclone.homeScreen.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapp.swiggyclone.homeScreen.homeRepository.HomeRespository
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse

class HomeViewModel:ViewModel() {


    //CREATE ALL THE REQUIRED RESPONSE MUTABLE LIVE DATAS.
    //CREATE ALL THE NEEDED REPOSITORY YOU NEED TO CALL IN THIS VIEW-MODEL
    var homeMutableLiveData:MutableLiveData<HomeResponse> = MutableLiveData()

    var isFailed: LiveData<String> = MutableLiveData<String>()
    var isConnecting: LiveData<String> = MutableLiveData<String>()

    fun getIsFailed(): LiveData<String> {
        return isFailed
    }
    fun getIsConnecting(): LiveData<String> {
        return isConnecting
    }
    var homeRepository:HomeRespository?=null
    init {
        homeRepository = HomeRespository()
    }

    //Calling the Home Api.
    fun getHomeResponse(context: Context,accessToken:String):MutableLiveData<HomeResponse>{
        homeMutableLiveData = homeRepository!!.callHomeApi(context,accessToken)
        return homeMutableLiveData as MutableLiveData<HomeResponse>

    }



}