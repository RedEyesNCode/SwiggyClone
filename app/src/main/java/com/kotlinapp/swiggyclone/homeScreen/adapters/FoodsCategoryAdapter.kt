package com.kotlinapp.swiggyclone.homeScreen.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kotlinapp.swiggyclone.auth.view.fragments.LoginFragment
import com.kotlinapp.swiggyclone.auth.view.fragments.SignupFragment
import com.kotlinapp.swiggyclone.homeScreen.view.fragments.*

class FoodsCategoryAdapter(fragmentManager: FragmentManager, context: Context?, totalTabs: Int) : FragmentPagerAdapter(fragmentManager,totalTabs) {
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
                return FoodFragment()
            }
            1 -> {
                return SnacksFragment()
            }
            2 -> {

                return ItalianFragment()
            }
            3 -> {
                return SaucesFragment()
            }
            4 -> {
                return  SouthIndianFragment()

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
                return "FOOD"
            }
            1 -> {
                return "Snacks"
            }
            2 -> {
                return "Italian"
            }
            3 -> {
                return "Sauces"
            }
            4 -> {
                return "South-Indian"
            }
            else -> ""
        }
    }

}