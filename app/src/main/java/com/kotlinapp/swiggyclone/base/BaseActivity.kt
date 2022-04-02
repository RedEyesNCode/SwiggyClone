package com.kotlinapp.swiggyclone.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity :AppCompatActivity() {


    private var commonProgress:CommonProgressDialog ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonProgress = CommonProgressDialog(this@BaseActivity)

    }

    fun hideLoader(){
        try {
            if (commonProgress != null && commonProgress!!.isShowing()) {
                commonProgress!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun showLoader(){

        try {
            if (commonProgress != null && commonProgress!!.isShowing()) {
                commonProgress!!.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }



    }



    fun showLog(string:String){
        Log.i("ALLTHISFOR4HEARTS",string)
    }



}