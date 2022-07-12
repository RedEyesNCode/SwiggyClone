package com.kotlinapp.swiggyclone.homeScreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.base.BaseActivity
import com.kotlinapp.swiggyclone.databinding.ActivityHomeBinding
import com.kotlinapp.swiggyclone.searchCuisines.FragmentSearchCuisines
import com.kotlinapp.swiggyclone.searchFood.SearchFoodFragment
import com.kotlinapp.swiggyclone.userAccount.view.UserAccountFragment
import com.kotlinapp.swiggyclone.utils.FragmentUtils

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Replacing the home Fragment the home Fragment
        initViewClick()

    }
    fun initViewClick(){
        val navController: NavController =
            Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        val bottomNavigationView = binding.bottomNavigation
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }

    fun bottomNavigationVisibilty(visibility:Int){
        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.bottomNavigation.visibility = visibility


    }


}