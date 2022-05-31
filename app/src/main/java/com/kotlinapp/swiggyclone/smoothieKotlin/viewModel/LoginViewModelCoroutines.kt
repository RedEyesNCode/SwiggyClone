package com.kotlinapp.swiggyclone.smoothieKotlin.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.utils.Resource
import androidx.lifecycle.viewModelScope
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.utils.Event
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException

class LoginViewModelCoroutines(app:Application,private val appRepository: AppRepository):AndroidViewModel(app) {

    private val _loginResponse = MutableLiveData<Event<Resource<LoginDataClass>>>()
    val loginResponse: LiveData<Event<Resource<LoginDataClass>>> = _loginResponse


    fun loginUser(body: LoginInputBody) = viewModelScope.launch {
        login(body)
    }

    private suspend fun login(body: LoginInputBody) {
        // THE BELOW LINE WILL SEND THE DATA NULL. SO MANAGE IT ACCORDINGLY IN THE VIEW.
        _loginResponse.postValue(Event(Resource.Loading()))
        try {
            val response = appRepository.loginUserCoroutine(body)
            //change here from response
            _loginResponse.postValue(handlerloginResponse(response.awaitResponse()) as Event<Resource<LoginDataClass>>?)

        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _loginResponse.postValue(
                        Event(Resource.Error("EXCEPTION >>"+t.message)
                    ))
                    Log.i("SWIGGY_CLONE",t.message!!)

                }
                else -> {
                    _loginResponse.postValue(
                        Event(Resource.Error("Conversion Error"+t.message))
                    )
                    Log.i("SWIGGY_CLONE",t.message!!)
                }
            }
        }
    }

    private fun handlerloginResponse(response: Response<LoginDataClass>): Any {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return ""
    }

}