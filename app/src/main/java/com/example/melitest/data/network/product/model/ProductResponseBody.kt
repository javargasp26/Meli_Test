package com.example.melitest.data.network.product.model

import com.example.melitest.data.network.retrofit.ResponseBody
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductResponseBody : ResponseBody() {
    @SerializedName("body")
    @Expose
    var results1: ProductItem? = null
}

class ProductItem(
    @SerializedName("results")
    @Expose
    var results: List<ProductResponse>? = null
)