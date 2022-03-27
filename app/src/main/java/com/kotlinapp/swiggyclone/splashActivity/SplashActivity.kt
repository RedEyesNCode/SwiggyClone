package com.kotlinapp.swiggyclone.splashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.auth.view.LoginActivity
import com.kotlinapp.swiggyclone.databinding.ActivitySplashBinding
import com.kotlinapp.swiggyclone.homeScreen.HomeActivity

class SplashActivity : AppCompatActivity() {

    //Intializing the view binding
    lateinit var activitySplashBinding : ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(activitySplashBinding.root)


        var handler = Handler().postDelayed(Runnable {

            var intentHome = Intent(this,LoginActivity::class.java)
            startActivity(intentHome)


        },3000)


    }
}