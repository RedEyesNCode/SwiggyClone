package com.kotlinapp.swiggyclone.productDetail.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kotlinapp.swiggyclone.auth.view.fragments.LoginFragment
import com.kotlinapp.swiggyclone.auth.view.fragments.SignupFragment
import com.kotlinapp.swiggyclone.productDetail.model.Datum
import com.kotlinapp.swiggyclone.productDetail.singleProductView.SingleProductFragment

class ProductsViewpager (fragmentManager: FragmentManager, context: Context?, totalTabs: Int,productsList:ArrayList<Datum>) : FragmentPagerAdapter(fragmentManager,totalTabs){



    private var myContext: Context? = null
    var totalTabs = totalTabs
    var productsList = productsList

    fun ProductsViewpager(context: Context?, fm: FragmentManager?, totalTabs: Int) {
        myContext = context
        this.totalTabs = totalTabs
    }

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        // LOGIC FOR ADDING THE VIEW PAGER DYNAMICALLY.
        for (product in productsList){
            return SingleProductFragment.newInstance(product.dishName!!,"")
        }
        return SingleProductFragment.newInstance("productsList.get(position).dishName.toString()","")







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