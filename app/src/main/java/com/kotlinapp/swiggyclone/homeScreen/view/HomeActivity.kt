package com.kotlinapp.swiggyclone.homeScreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
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
//
//        fragmentUtils.replaceFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,
//            HomeFragment(),
//            HomeFragment().tagFragment,true)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainHomeContainer) as NavHostFragment
        val navHostController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navHostController)

        binding.bottomNavigation.menu.get(0).isChecked = true


        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.swiggyhome-> fragmentUtils.replaceFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,
                    HomeFragment(),
                    HomeFragment().tagFragment,false)
                R.id.food-> fragmentUtils.replaceFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,SearchFoodFragment(),SearchFoodFragment().tagFragment,false)
                R.id.search->fragmentUtils.replaceFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,FragmentSearchCuisines(),FragmentSearchCuisines().tagFragment,false)
                R.id.account->fragmentUtils.replaceFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,UserAccountFragment(),UserAccountFragment().tagFragment,false)


            }
            true
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        //remember backstack count should be zero and then incase of 0 go to homefragment and if instance of homefragment exit app.

        if(supportFragmentManager.backStackEntryCount==0){
            FragmentUtils().replaceFragmentBackStack(supportFragmentManager,R.id.mainHomeContainer,
                HomeFragment(),
                HomeFragment().tagFragment,false)

        }else if(supportFragmentManager.fragments[0]==HomeFragment()){
            finish()
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