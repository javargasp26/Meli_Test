package com.example.melitest.data.network.retrofit

import com.example.melitest.data.network.product.model.ProductResponse
import com.example.melitest.data.network.retrofit.PagingBody
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ResponseBody {
    @SerializedName("site_id")
    @Expose
    val site_id: String? = null

    @SerializedName("query")
    @Expose
    val query: String? = null

    @SerializedName("paging")
    @Expose
    val paging: PagingBody? = null

    @SerializedName("results")
    @Expose
    val results: List<ProductResponse>? = null
}