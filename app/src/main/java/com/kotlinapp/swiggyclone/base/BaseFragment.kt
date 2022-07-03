package com.kotlinapp.swiggyclone.base

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant

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
    fun getAccessToken(): String {
        var accessToken =AppSession(requireContext()).getString(Constant().ACCESS_TOKEN)

        if(accessToken!=null){
            return accessToken
        }else{
            return ""
        }
    }
    fun getUserId():String{
        var userID  = AppSession(requireContext()).getValue(Constant().USER_ID,requireContext())
        if(userID!=null){
            return userID
        }else{
            return ""
        }


    }
    fun addFragmentBackStackFullContainer(fragment:Fragment,tag:String, isAddtoBackSTack: Boolean){

        var fragmentTransaction : FragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.add(R.id.mainHomeContainer, fragment);
        if (isAddtoBackSTack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();

    }






}