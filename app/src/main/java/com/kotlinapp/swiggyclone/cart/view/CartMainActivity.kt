package com.kotlinapp.swiggyclone.cart.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.utils.FragmentUtils

class CartMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_main)

        FragmentUtils().addFragmentBackStack(supportFragmentManager,R.id.cartcontainer,CartFragment(),CartFragment.javaClass.simpleName,true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount==0){
            finish()
        }
    }
}