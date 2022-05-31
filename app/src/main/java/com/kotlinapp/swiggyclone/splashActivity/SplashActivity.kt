package com.kotlinapp.swiggyclone.splashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kotlinapp.swiggyclone.auth.view.LoginActivity
import com.kotlinapp.swiggyclone.databinding.ActivitySplashBinding
import com.kotlinapp.swiggyclone.homeScreen.view.HomeActivity
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant

class SplashActivity : AppCompatActivity() {

    //Intializing the view binding
    lateinit var activitySplashBinding : ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(activitySplashBinding.root)


        var handler = Handler().postDelayed(Runnable {

            var islogin = AppSession(this@SplashActivity).getString(Constant().IS_LOGIN)
            if(islogin!!.contains("true")){
                var intentHome = Intent(this,HomeActivity::class.java)
                intentHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intentHome)
            }else{
                var intentHome = Intent(this,LoginActivity::class.java)
                intentHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intentHome)
            }

        },3000)


    }
}