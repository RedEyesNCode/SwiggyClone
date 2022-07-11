package com.kotlinapp.swiggyclone.homeScreen.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kotlinapp.swiggyclone.auth.view.fragments.LoginFragment
import com.kotlinapp.swiggyclone.auth.view.fragments.SignupFragment
import com.kotlinapp.swiggyclone.homeScreen.view.fragments.*

class FoodsCategoryAdapter(fragmentManager: FragmentManager, context: Context?, datalist:ArrayList<Fragment>) : FragmentPagerAdapter(fragmentManager,datalist.size) {
    private var myContext: Context? = null
    var totalTabs = datalist.size
    var fragments= datalist

    fun MyAdapter(context: Context?, fm: FragmentManager?, totalTabs: Int,datalist: ArrayList<Fragment> ) {
        myContext = context
        this.totalTabs = totalTabs
        this.fragments = datalist
    }

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
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