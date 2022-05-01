package com.kotlinapp.swiggyclone.searchFood.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapp.swiggyclone.homeScreen.models.AllRestaurantsResponseData
import com.kotlinapp.swiggyclone.searchFood.repository.SearchFoodRepository

class SearchFoodViewModel :ViewModel() {


    var isFailed: LiveData<String> = MutableLiveData<String>()
    var isConnecting: LiveData<String> = MutableLiveData<String>()

    fun getIsFailed(): LiveData<String> {
        return isFailed
    }
    fun getIsConnecting(): LiveData<String> {
        return isConnecting
    }

    var allRestaurantsResponseDataMutableLiveData:MutableLiveData<AllRestaurantsResponseData> = MutableLiveData()
    var searchFoodRepository:SearchFoodRepository?=null

    init {
        searchFoodRepository = SearchFoodRepository()
    }

    //Calling the get all restaurants api from the search food repository.

    fun getAllRestaurants(accessToken:String,context: Context):MutableLiveData<AllRestaurantsResponseData>{
        allRestaurantsResponseDataMutableLiveData = searchFoodRepository!!.getAllRestaurants(accessToken, context)

        return  allRestaurantsResponseDataMutableLiveData



    }



}