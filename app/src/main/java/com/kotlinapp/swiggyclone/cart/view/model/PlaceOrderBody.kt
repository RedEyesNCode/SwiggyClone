package com.kotlinapp.swiggyclone.cart.view.model

import com.google.gson.annotations.SerializedName

data class PlaceOrderBody(@SerializedName("orderId"      ) var orderId      : Int?    = null,
                          @SerializedName("userId"       ) var userId       : Int?    = null,
                          @SerializedName("addressId"    ) var addressId    : Int?    = null,
                          @SerializedName("orderName"    ) var orderName    : String? = null,
                          @SerializedName("provider"     ) var provider     : String? = null,
                          @SerializedName("customerName" ) var customerName : String? = null,
                          @SerializedName("orderStatus"  ) var orderStatus  : String? = null,
                          @SerializedName("amount"       ) var amount       : Int?    = null
)
