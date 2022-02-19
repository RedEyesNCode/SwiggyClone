package com.kotlinapp.swiggyclone.homeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.databinding.ActivityHomeBinding
import com.kotlinapp.swiggyclone.homeScreen.adapters.SwiggyServiceAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recvSwiggyservices.adapter = SwiggyServiceAdapter(initViewStatic(),this)
        binding.recvSwiggyservices.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    fun initViewStatic(): List<String>{

        var list = mutableListOf<String>()
        list.add("Food")
        list.add("Insta Mart")
        list.add("Genie")
        return list


    }
}