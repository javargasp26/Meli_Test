package com.example.melitest.data.network.product.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductResponse {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("seller")
    @Expose
    val seller: Seller? = null

    @SerializedName("price")
    @Expose
    val price: Double? = null

    @SerializedName("currency_id")
    @Expose
    val currency_id: String? = null

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String? = null

    @SerializedName("tags")
    @Expose
    val tags: List<String>? = null

}
