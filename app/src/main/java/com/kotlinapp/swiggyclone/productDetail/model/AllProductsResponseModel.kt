package com.kotlinapp.swiggyclone.productDetail.model

import com.google.gson.annotations.SerializedName

data class AllProductsResponseModel(@SerializedName("status"  ) var status  : String?         = null,
                                    @SerializedName("code"    ) var code    : Int?            = null,
                                    @SerializedName("message" ) var message : String?         = null,
                                    @SerializedName("data"    ) var data    : ArrayList<Datum> = arrayListOf()
)
data class Datum (

    @SerializedName("menuId"      ) var menuId      : Int?     = null,
    @SerializedName("dishName"    ) var dishName    : String?  = null,
    @SerializedName("price"       ) var price       : Int?     = null,
    @SerializedName("description" ) var description : String?  = null,
    @SerializedName("productType" ) var productType : String?  = null,
    @SerializedName("veg"         ) var veg         : Boolean? = null,
    @SerializedName("productImage") var productImage :String?=null

)