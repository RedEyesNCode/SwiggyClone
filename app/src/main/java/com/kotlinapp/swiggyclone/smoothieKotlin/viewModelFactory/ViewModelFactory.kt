package com.kotlinapp.swiggyclone.smoothieKotlin.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.homeScreen.viewModel.HomeViewModel
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModel.LoginViewModelCoroutines
import com.kotlinapp.swiggyclone.userAccount.viewModel.AccountViewModel

class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    // ADD ALL YOUR VIEW MODELS IN A SINGLE FACTORY.
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModelCoroutines::class.java)) {
            return LoginViewModelCoroutines(app, appRepository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(app, appRepository) as T
        }else if(modelClass.isAssignableFrom(AccountViewModel::class.java)){
            return AccountViewModel(app, appRepository) as T
        }else{
            throw IllegalArgumentException("Unknown class name")

        }
    }

}