package com.example.melitest.data.network.product.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Seller {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("power_seller_status")
    @Expose
    val power_seller_status: String? = null

    @SerializedName("car_dealer")
    @Expose
    val car_dealer: Boolean? = null

    @SerializedName("real_estate_agency")
    @Expose
    val real_estate_agency: Boolean? = null
}

