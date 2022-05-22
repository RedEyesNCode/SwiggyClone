package com.kotlinapp.swiggyclone.auth.view.fragments.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kotlinapp.swiggyclone.auth.view.fragments.LoginFragment
import com.kotlinapp.swiggyclone.auth.view.fragments.SignupFragment
import com.kotlinapp.swiggyclone.homeScreen.view.HomeFragment




class TabAdapter(fragmentManager: FragmentManager,context: Context?,totalTabs: Int) : FragmentPagerAdapter(fragmentManager,totalTabs) {
    private var myContext: Context? = null
    var totalTabs = totalTabs

    fun MyAdapter(context: Context?, fm: FragmentManager?, totalTabs: Int) {
        myContext = context
        this.totalTabs = totalTabs
    }

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
               return LoginFragment()
            }
            1 -> {
                return SignupFragment()
            }

            else -> return LoginFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> {
                return "LOGIN"
            }
            1 -> {
                return "SIGNUP"
            }
            else -> ""
        }
    }

}