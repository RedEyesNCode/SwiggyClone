package com.kotlinapp.swiggyclone.productDetail.model

import com.google.gson.annotations.SerializedName

data class ProductTypeResponse(@SerializedName("productTypeId" ) var productTypeId : Int?    = null,
                               @SerializedName("productType"   ) var productType   : String? = null)
