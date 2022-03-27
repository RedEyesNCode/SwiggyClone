package com.kotlinapp.swiggyclone.base

import android.os.Bundle
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

}