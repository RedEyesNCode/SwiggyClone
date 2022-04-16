package com.kotlinapp.swiggyclone.restaurantDetail

import com.google.gson.annotations.SerializedName

class GetWishlistResponseData(@SerializedName("status"  ) var status  : String?         = null,
                              @SerializedName("code"    ) var code    : Int?            = null,
                              @SerializedName("message" ) var message : String?         = null,
                              @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()) {

    data class Data (

        @SerializedName("wishlistId"     ) var wishlistId     : Int?     = null,
        @SerializedName("userId"         ) var userId         : Int?     = null,
        @SerializedName("restaurantName" ) var restaurantName : String?  = null,
        @SerializedName("rating"         ) var rating         : Int?     = null,
        @SerializedName("deliveryTime"   ) var deliveryTime   : String?  = null,
        @SerializedName("discount"       ) var discount       : Int?     = null,
        @SerializedName("swiggyOne"      ) var swiggyOne      : Boolean? = null,
        @SerializedName("added"          ) var added          : Boolean? = null

    )
}