package com.kotlinapp.swiggyclone.homeScreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.databinding.ActivityHomeBinding
import com.kotlinapp.swiggyclone.searchCuisines.FragmentSearchCuisines
import com.kotlinapp.swiggyclone.searchFood.SearchFoodFragment
import com.kotlinapp.swiggyclone.userAccount.view.UserAccountFragment
import com.kotlinapp.swiggyclone.utils.FragmentUtils

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Replacing the home Fragment the home Fragment
        initViewClick()

    }
    fun initViewClick(){
        var fragmentUtils = FragmentUtils()

        fragmentUtils.addFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,
            HomeFragment(),
            HomeFragment().tagFragment,true)
        binding.btnUserAccount.setOnClickListener {

            fragmentUtils.addFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,UserAccountFragment(),UserAccountFragment().tagFragment,true)

        }
        binding.btnHome.setOnClickListener {
            fragmentUtils.addFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,
                HomeFragment(),
                HomeFragment().tagFragment,true)

        }
        binding.btnFood.setOnClickListener {
           // fragmentUtils.addFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,HomeFragment(),HomeFragment().tagFragment,true)
            System.out.println("BTN_FOOD_CLICK")
            fragmentUtils.addFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,SearchFoodFragment(),SearchFoodFragment().tagFragment,true)
        }
        binding.btnSearch.setOnClickListener {

            fragmentUtils.addFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,FragmentSearchCuisines(),FragmentSearchCuisines().tagFragment,true)

        }




    }

    fun initViewStatic(): List<String>{

        var list = mutableListOf<String>()
        list.add("Food")
        list.add("Insta Mart")
        list.add("Genie")
        return list


    }
}