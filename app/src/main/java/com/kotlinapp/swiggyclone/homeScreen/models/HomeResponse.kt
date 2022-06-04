package com.kotlinapp.swiggyclone.homeScreen.models

import com.google.gson.annotations.SerializedName

data class HomeResponse(  @SerializedName("status"      ) var status      : String?                = null,
                          @SerializedName("code"        ) var code        : Int?                   = null,
                          @SerializedName("message"     ) var message     : String?                = null,
                          @SerializedName("restaurants" ) var restaurants : ArrayList<Restaurants> = arrayListOf(),
                          @SerializedName("offers"      ) var offers      : ArrayList<Offers>      = arrayListOf(),
                          @SerializedName("brands"      ) var brands      : ArrayList<Brands>      = arrayListOf(),
                          @SerializedName("curations"   ) var curations   : ArrayList<Curations>   = arrayListOf()
)
data class Restaurants (

    @SerializedName("restaurantId"   ) var restaurantId   : Int?     = null,
    @SerializedName("restaurantName" ) var restaurantName : String?  = null,
    @SerializedName("rating"         ) var rating         : Double?     = null,
    @SerializedName("deliveryTime"   ) var deliveryTime   : String?  = null,
    @SerializedName("discount"       ) var discount       : Double?     = null,
    @SerializedName("swiggyOne"      ) var swiggyOne      : Boolean? = null,
    @SerializedName("restaurantImage" )var restaurantImage :String?=null

)
data class Offers (

    @SerializedName("offerId"    ) var offerId    : Int?    = null,
    @SerializedName("discount"   ) var discount   : Double?    = null,
    @SerializedName("couponCode" ) var couponCode : String? = null

)
data class Brands (

    @SerializedName("brandId"        ) var brandId        : Int?    = null,
    @SerializedName("restaurantId"   ) var restaurantId   : Int?    = null,
    @SerializedName("restaurantName" ) var restaurantName : String? = null,
    @SerializedName("ratings"        ) var ratings        : Double? = null,
    @SerializedName("deliveryTime"   ) var deliveryTime   : String? = null

)
data class Curations (

    @SerializedName("curationId"  ) var curationId  : Int?    = null,
    @SerializedName("cuisineName" ) var cuisineName : String? = null

)