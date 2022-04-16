package com.kotlinapp.swiggyclone.homeScreen.models

import com.google.gson.annotations.SerializedName

data class AllRestaurantsResponseData(@SerializedName("status"  ) var status  : String?         = null,
                                      @SerializedName("code"    ) var code    : Int?            = null,
                                      @SerializedName("message" ) var message : String?         = null,
                                      @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf())

data class Data (

    @SerializedName("restaurantId"   ) var restaurantId   : Int?     = null,
    @SerializedName("restaurantName" ) var restaurantName : String?  = null,
    @SerializedName("rating"         ) var rating         : Int?     = null,
    @SerializedName("deliveryTime"   ) var deliveryTime   : String?  = null,
    @SerializedName("discount"       ) var discount       : Int?     = null,
    @SerializedName("swiggyOne"      ) var swiggyOne      : Boolean? = null

)
