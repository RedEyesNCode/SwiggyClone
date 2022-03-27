package com.kotlinapp.swiggyclone.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.base.BaseActivity
import com.kotlinapp.swiggyclone.databinding.ActivityLoginBinding
import com.kotlinapp.swiggyclone.homeScreen.HomeActivity

class LoginActivity : BaseActivity() {

    private var binding:ActivityLoginBinding?=null
    private lateinit var loginViewModel:LoginViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initViewClick()


        loginViewModel.testApi(this@LoginActivity)

        //By !! this operator we are telling kotlin that this can't be null we are sure of that
        loginViewModel.verifyOtpLiveData!!.observe(this, Observer {

            showLoader()
                Toast.makeText(this@LoginActivity,it.message,Toast.LENGTH_LONG).show()
        })





    }
    fun initViewClick(){
        binding!!.btnLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }



    }
}