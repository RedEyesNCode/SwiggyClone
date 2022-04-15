package com.kotlinapp.swiggyclone.auth.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.base.BaseActivity
import com.kotlinapp.swiggyclone.databinding.ActivityLoginBinding
import com.kotlinapp.swiggyclone.homeScreen.view.HomeActivity
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant


class LoginActivity : BaseActivity() {

    private var binding:ActivityLoginBinding?=null
    private lateinit var loginViewModel:LoginViewModel
    private var loginLiveData: MutableLiveData<LoginDataClass>?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = this!!.run {
            ViewModelProvider(this).get(LoginViewModel::class.java)
        }
       // loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
/*
        loginViewModel = LoginViewModel()
*/
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initViewClick()

        loginViewModel.testApi(this)

        loginViewModel.testApi(this@LoginActivity)

        //By !! this operator we are telling kotlin that this can't be null we are sure of that
    }
    fun initViewClick(){
        var loginInputBody = LoginInputBody()
        loginInputBody.password=""
        loginInputBody.username=""

        binding!!.btnLogin.setOnClickListener {
            var number:String = binding!!.edtNumber.text.toString()
            var password:String = binding!!.edtPassword.text.toString()
            //Here we are defining that the binding variable cannot be null;
            var loginInputBody = LoginInputBody()
            loginInputBody.password = password
            loginInputBody.username = number
            showLoader()
           loginViewModel.loginApiCall(this@LoginActivity, loginInputBody)
            attachObservers()

        }



    }
    fun attachObservers(){
        loginViewModel.getLoginResponseLiveData().observe(this, Observer {

            hideLoader()
            if (it.code == 200 && it.status!!.contains("success")) {

                //NOT USING THE CONSTANT STRING CLASS BECAUSE IT CAN'T STORE THE SPECIFIC VALUES IN THE STRING
                AppSession(this@LoginActivity).setValue(Constant().USER_ID,it.data!!.id.toString(),this@LoginActivity)
                AppSession(this@LoginActivity).setValue(Constant().ACCESS_TOKEN,it.Token,this@LoginActivity)

                var stringAccessToken  = AppSession(this).getValue(Constant().ACCESS_TOKEN,this)
                var userID  = AppSession(this).getValue(Constant().USER_ID,this)

                if(stringAccessToken==null){
                    showLog("ERROR_SHARED_PREFERENCES")
                    Toast.makeText(this@LoginActivity,"USER SESSION NOT CREATED !",Toast.LENGTH_SHORT).show()

                }else{
                        showLog("ACCESS_TOKEN : STORED : "+stringAccessToken)
                    showLog("USER:ID : STORED : "+userID)
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))


                }
            }else{

                Toast.makeText(this@LoginActivity,"Login Failed",Toast.LENGTH_SHORT).show()
            }
        })










        }

}




//Save token here
//THE MOST BASIC SHARED PREFERENCES METHOD IN KOTLIN

/* //Save token here
 val token = it.Token
 val preferences: SharedPreferences =
     this@LoginActivity.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
 preferences.edit().putString("TOKEN", token).apply()


 //Retrieve token wherever necessary


 //Retrieve token wherever necessary
 val preferences2: SharedPreferences =
     this@LoginActivity.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
 val retrivedToken =
     preferences2.getString("TOKEN", null) //second parameter default value.
 showLog(retrivedToken+"NEW")*/