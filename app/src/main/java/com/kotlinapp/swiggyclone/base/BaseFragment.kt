package com.kotlinapp.swiggyclone.base

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    private var commonProgress:CommonProgressDialog ?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        commonProgress = CommonProgressDialog(context)

    }

    fun hideLoader(){
        try {
            if (commonProgress != null && commonProgress!!.isShowing()) {
                commonProgress?.dissmissDialog()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showToast(e.message.toString())
        }
    }
    fun showLoader(){

        try {
            if (commonProgress != null && commonProgress!!.isShowing()) {
                commonProgress?.showDialog()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showToast(e.message.toString())

        }



    }
    fun showToast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
    fun showLog(message: String){
        Log.i("SWIGGY_CLONE",message)


    }





}