package com.kotlinapp.swiggyclone.utils

import android.content.Context
import android.util.Log
import androidx.annotation.Nullable
import com.google.gson.GsonBuilder
import com.kotlinapp.swiggyclone.auth.model.CommonStatusMessageResponse
import okhttp3.ResponseBody

open class AppUtils {
    fun getServerError(responseCode: Int, responseBody: ResponseBody?, @Nullable context: Context?): String? {
        var serverHandling = "Error $responseCode Please try again."
        when (responseCode) {
            401 -> {
                //LogOutTheUser
                /*Toast.makeText(context, "Unauthorized Access : Invalid Token Error : 401", Toast.LENGTH_SHORT).show();*/serverHandling =
                    "Session Timed Out."
            }
            400 ->                 //Bad Request Display The Message
                try {
                    if (responseBody != null) {
                        val gson = GsonBuilder().create()
                        var commonStatusMessageResponseOne = CommonStatusMessageResponse()
                        commonStatusMessageResponseOne = gson.fromJson(
                            responseBody.string(),
                            CommonStatusMessageResponse::class.java
                        )
                        commonStatusMessageResponseOne!!.message?.let { Log.i("ERROR_BODY", it) }
                        serverHandling = commonStatusMessageResponseOne!!.message.toString()
                        return serverHandling
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
        }
        return serverHandling
    }
    fun showLog(message: String){
        Log.i("SWIGGY_CLONE",message)


    }


}