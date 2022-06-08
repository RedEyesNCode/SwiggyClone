package com.kotlinapp.swiggyclone.productDetail.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetailResponse(  @SerializedName("status"  ) var status  : String? = null,
                                      @SerializedName("code"    ) var code    : Int?    = null,
                                      @SerializedName("message" ) var message : String? = null,
                                      @SerializedName("data"    ) var data    : Data?   = Data())
data class Data (

    @SerializedName("restaurantDetailId" ) var restaurantDetailId : Int?    = null,
    @SerializedName("cuisines"           ) var cuisines           : String? = null,
    @SerializedName("location"           ) var location           : String? = null,
    @SerializedName("lat"                ) var lat                : Double? = null,
    @SerializedName("lng"                ) var lng                : Double? = null,
    @SerializedName("rating"             ) var rating             : Double?    = null,
    @SerializedName("time"               ) var time               : String? = null,
    @SerializedName("price"              ) var price              : String? = null,
    @SerializedName("restaurantName"     ) var restaurantName     : String? = null,

)