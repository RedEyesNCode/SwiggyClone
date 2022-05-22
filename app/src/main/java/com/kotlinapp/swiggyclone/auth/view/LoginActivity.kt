package com.kotlinapp.swiggyclone.auth.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.kotlinapp.swiggyclone.auth.model.LoginDataClass
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.auth.view.fragments.viewpager.TabAdapter
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.base.BaseActivity
import com.kotlinapp.swiggyclone.databinding.ActivityLoginBinding
import com.kotlinapp.swiggyclone.homeScreen.view.HomeActivity
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant
import kotlin.math.log


class LoginActivity : BaseActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var loginViewModel:LoginViewModel
    private var loginLiveData: MutableLiveData<LoginDataClass>?=null
    private lateinit var tabAdapter:TabAdapter

    //CODE FOR TAB LAYOUT




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*loginViewModel = this!!.run {
            ViewModelProvider(this).get(LoginViewModel::class.java)
        }*/
        // CHANGING ON HOW THE VIEW MODEL SHOULD BE INTIALZED
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        //Intialzed just like java
        attachObservers()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        intializeTabs()

        setContentView(binding!!.root)
        initViewClick()

        loginViewModel.testApi(this)

        loginViewModel.testApi(this@LoginActivity)

        //By !! this operator we are telling kotlin that this can't be null we are sure of that
    }

    fun intializeTabs() {
        tabAdapter = TabAdapter(supportFragmentManager, this@LoginActivity,binding.tablayout.tabCount)
        binding.tabsViewpager.adapter = tabAdapter
        binding.tablayout.setupWithViewPager(binding.tabsViewpager)
        binding.tabsViewpager.addOnPageChangeListener(TabLayoutOnPageChangeListener(binding.tablayout))

        binding.tablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.tabsViewpager.setCurrentItem(tab.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    fun initViewClick(){
        var loginInputBody = LoginInputBody()
        loginInputBody.password=""
        loginInputBody.username=""

    }
    fun attachObservers(){
        loginViewModel.getLoginResponseLiveData().observe(this, Observer {

            hideLoader()
            if (it.code == 200 && it.status!!.contains("success")) {

                //NOT USING THE CONSTANT STRING CLASS BECAUSE IT CAN'T STORE THE SPECIFIC VALUES IN THE STRING
                    AppSession(this@LoginActivity).clearAll()


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