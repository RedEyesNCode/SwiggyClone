package com.kotlinapp.swiggyclone.cart.view.model

import com.google.gson.annotations.SerializedName

data class GetCartResponseModel( @SerializedName("status"  ) var status  : String?         = null,
                                 @SerializedName("code"    ) var code    : Int?            = null,
                                 @SerializedName("message" ) var message : String?         = null,
                                 @SerializedName("cart"    ) var cart    : ArrayList<Cart> = arrayListOf()

)
data class RestaurantDetail (

    @SerializedName("restaurantDetailId" ) var restaurantDetailId : Int?    = null,
    @SerializedName("cuisines"           ) var cuisines           : String? = null,
    @SerializedName("location"           ) var location           : String? = null,
    @SerializedName("lat"                ) var lat                : Double? = null,
    @SerializedName("lng"                ) var lng                : Double? = null,
    @SerializedName("rating"             ) var rating             : Int?    = null,
    @SerializedName("time"               ) var time               : String? = null,
    @SerializedName("price"              ) var price              : String? = null,
    @SerializedName("restaurantName"     ) var restaurantName     : String? = null

)
data class Data (

    @SerializedName("restaurantId"     ) var restaurantId     : Int?              = null,
    @SerializedName("restaurantDetail" ) var restaurantDetail : RestaurantDetail? = RestaurantDetail()

)
data class ProductData (

    @SerializedName("menuId"       ) var menuId       : Int?     = null,
    @SerializedName("dishName"     ) var dishName     : String?  = null,
    @SerializedName("price"        ) var price        : Int?     = null,
    @SerializedName("description"  ) var description  : String?  = null,
    @SerializedName("productType"  ) var productType  : String?  = null,
    @SerializedName("productImage" ) var productImage : String?  = null,
    @SerializedName("veg"          ) var veg          : Boolean? = null

)
data class Product (

    @SerializedName("productId"   ) var productId   : Int?         = null,
    @SerializedName("productData" ) var productData : ProductData? = ProductData()

)
data class PaymentDetail (

    @SerializedName("paymentId"   ) var paymentId   : Int?    = null,
    @SerializedName("orderId"     ) var orderId     : Int?    = null,
    @SerializedName("userId"      ) var userId      : Int?    = null,
    @SerializedName("amount"      ) var amount      : Int?    = null,
    @SerializedName("provider"    ) var provider    : String? = null,
    @SerializedName("orderStatus" ) var orderStatus : String? = null,
    @SerializedName("createdAt"   ) var createdAt   : String? = null

)
data class Cart (

    @SerializedName("orderId"       ) var orderId       : Int?           = null,
    @SerializedName("data"          ) var data          : Data?          = Data(),
    @SerializedName("product"       ) var product       : Product?       = Product(),
    @SerializedName("paymentDetail" ) var paymentDetail : PaymentDetail? = PaymentDetail()

)