package com.example.melitest.presentation.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Seller(
    val id: String,
    val power_seller_status: String,
    val car_dealer: Boolean,
    val real_estate_agency: Boolean
) : Parcelable