package com.example.melitest.presentation.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String,
    val title: String,
    val seller:Seller,
    val price: Double,
    val currency_id: String,
    val thumbnail:String,
    val tags: List<String>
) : Parcelable
