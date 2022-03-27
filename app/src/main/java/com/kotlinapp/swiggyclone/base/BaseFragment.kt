package com.kotlinapp.swiggyclone.base

import android.content.Context
import androidx.fragment.app.Fragment

class BaseFragment : Fragment() {


    private var commonProgress:CommonProgressDialog ?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        commonProgress = CommonProgressDialog(context)

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