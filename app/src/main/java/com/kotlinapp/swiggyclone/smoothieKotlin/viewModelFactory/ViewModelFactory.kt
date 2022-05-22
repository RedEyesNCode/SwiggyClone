package com.kotlinapp.swiggyclone.smoothieKotlin.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModel.LoginViewModelCoroutines

class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(LoginViewModelCoroutines::class.java)) {
            return LoginViewModelCoroutines(app, appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}