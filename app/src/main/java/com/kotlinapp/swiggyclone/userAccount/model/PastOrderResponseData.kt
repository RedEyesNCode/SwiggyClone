package com.kotlinapp.swiggyclone.userAccount.model

import com.google.gson.annotations.SerializedName

data class PastOrderResponseData( @SerializedName("status"     ) var status     : String?               = null,
                                  @SerializedName("code"       ) var code       : Int?                  = null,
                                  @SerializedName("message"    ) var message    : String?               = null,
                                  @SerializedName("pastOrders" ) var pastOrders : ArrayList<PastOrders> = arrayListOf())
data class PastOrders (

    @SerializedName("pastOrderId"        ) var pastOrderId      : Int?    = null,
    @SerializedName("userID"             ) var userID           : Int?    = null,
    @SerializedName("restaurantName"     ) var restaurantName   : String? = null,
    @SerializedName("location"           ) var location         : String? = null,
    @SerializedName("orderTotal"         ) var orderTotal       : Double?    = null,
    @SerializedName("orderStatus"        ) var orderStatus      : String? = null,
    @SerializedName("past_orders_detail" ) var pastOrdersDetail : String? = null

)