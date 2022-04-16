package com.kotlinapp.swiggyclone.base

import com.google.gson.annotations.SerializedName
import retrofit2.Call
data class  StatusCodeMessageModel(@SerializedName("code"    ) var code    : Int?    = null,
                                   @SerializedName("status"  ) var status  : String? = null,
                                   @SerializedName("message" ) var message : String? = null


) {
    override fun toString(): String {
        return "SendOtpModel(message=$message)"
    }
}
