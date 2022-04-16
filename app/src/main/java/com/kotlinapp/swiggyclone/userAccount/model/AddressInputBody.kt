package com.kotlinapp.swiggyclone.userAccount.model

import com.google.gson.annotations.SerializedName

data class AddressInputBody(@SerializedName("userId"      ) var userId      : Int?    = null,
                            @SerializedName("firstName"   ) var firstName   : String? = null,
                            @SerializedName("lastName"    ) var lastName    : String? = null,
                            @SerializedName("phoneNumber" ) var phoneNumber : String? = null,
                            @SerializedName("apartMent"   ) var apartMent   : String? = null,
                            @SerializedName("address"     ) var address     : String? = null,
                            @SerializedName("postalCode"  ) var postalCode  : String? = null,
                            @SerializedName("city"        ) var city        : String? = null)
