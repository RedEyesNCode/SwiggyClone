package com.kotlinapp.swiggyclone.homeScreen.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.kotlinapp.swiggyclone.homeScreen.homeRepository.HomeRespository
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.utils.Event
import com.kotlinapp.swiggyclone.utils.Resource
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import retrofit2.awaitResponse

// FOR A COROUTINES VIEW MODEL YOU NEED TO OVER-RIDE THE ANDROID-VIEW-MODEL and PRIMARY CONSTRUCTOR MUST HAVE
// Application,AppRepository in the constructor
class HomeViewModel(app: Application, private val appRepository: AppRepository):AndroidViewModel(app) {


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

    // MAKING CHANGES IN THIS ACCORDING TO THE KOTLIN CO-ROUTINES MAKE ASYNC CALLS ON A SEPERATE THREAD IN ANDROID.
// FOR A COROUTINES VIEW MODEL YOU NEED TO OVER-RIDE THE ANDROID-VIEW-MODEL and PRIMARY CONSTRUCTOR MUST HAVE
// Application,AppRepository in the constructor

    // Get the Event<Resource<YourModelClass> type of mutable live data.

    private val _homeResponse = MutableLiveData<Event<Resource<HomeResponse>>>()
    val homeResponse:LiveData<Event<Resource<HomeResponse>>> = _homeResponse


    // Make a function to call the api
    fun callHomeapi(accessToken:String) = viewModelScope.launch {

        // YOU CAN ONLY CALL SUSPEND FUNTIONS HERE SO CREATE ONE.
        callHomeSuspendFuntion(accessToken)

    }


    //CREATING A SUSPEND FUNCTIONS REQUIRED.
    private suspend fun callHomeSuspendFuntion(accessToken: String){
        _homeResponse.postValue(Event(Resource.Loading()))

        //THIS TRY CATCH BLOCK IS NESSCARY TO SOLVE THE JSON PARSING ERROR IF OCCURED ANY
        try {
            val response = appRepository.homeapiCoroutinesCall(accessToken)
            _homeResponse.postValue(handleHomeApiResponse(response.awaitResponse()) as Event<Resource<HomeResponse>>)

        }catch (t:Throwable){
            when(t){
                is IOException -> {
                    _homeResponse.postValue(Event(Resource.Error("EXCEPTION OCCURRED > "+t.message)))
                }
                else -> {
                    _homeResponse.postValue(Event(Resource.Error("Conversion Error"+t.message)))
                }
            }

        }
    }

    // CREATING A FUNCTION TO HANDLE THE HOME API RESPONSE
    private fun handleHomeApiResponse(response: Response<HomeResponse>):Any{
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return ""


    }





}