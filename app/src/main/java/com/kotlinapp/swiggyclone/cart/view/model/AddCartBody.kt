package com.kotlinapp.swiggyclone.cart.view.model

import com.google.gson.annotations.SerializedName

data class AddCartBody(

    @SerializedName("userId"       ) var userId       : Int? = null,
    @SerializedName("restaurantId" ) var restaurantId : Int? = null,
    @SerializedName("productId"    ) var productId    : Int? = null)
