package com.kotlinapp.swiggyclone.auth.model

import com.google.gson.annotations.SerializedName

data class CommonStatusMessageResponse(  @SerializedName("status"  ) var status  : String? = null,
                                         @SerializedName("code"    ) var code    : Int?    = null,
                                         @SerializedName("message" ) var message : String? = null)
