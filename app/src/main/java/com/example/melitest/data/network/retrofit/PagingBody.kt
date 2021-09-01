package com.example.melitest.data.network.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PagingBody {
    @SerializedName("total")
    @Expose
    val total: Int? = null

    @SerializedName("offset")
    @Expose
    val offset: Int? = null

    @SerializedName("limit")
    @Expose
    val limit: Int? = null

    @SerializedName("primary_results")
    @Expose
    val primary_results: Int? = null
}
