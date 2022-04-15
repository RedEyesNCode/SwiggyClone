package com.kotlinapp.swiggyclone.userAccount.model

import com.google.gson.annotations.SerializedName

data class AddressResponseData(@SerializedName("status"        ) var status        : String?                  = null,
                               @SerializedName("code"          ) var code          : Int?                     = null,
                               @SerializedName("message"       ) var message       : String?                  = null,
                               @SerializedName("addressTables" ) var addressTables : ArrayList<AddressTables> = arrayListOf()
)

data class AddressTables (

    @SerializedName("addressId"   ) var addressId   : Int?    = null,
    @SerializedName("userId"      ) var userId      : Int?    = null,
    @SerializedName("firstName"   ) var firstName   : String? = null,
    @SerializedName("lastName"    ) var lastName    : String? = null,
    @SerializedName("phoneNumber" ) var phoneNumber : String? = null,
    @SerializedName("apartMent"   ) var apartMent   : String? = null,
    @SerializedName("address"     ) var address     : String? = null,
    @SerializedName("postalCode"  ) var postalCode  : String? = null,
    @SerializedName("city"        ) var city        : String? = null

)
