package com.example.melitest.presentation


import com.example.melitest.data.network.product.model.ProductResponse
import com.example.melitest.presentation.model.Seller
class util {

    fun getSeller(product: ProductResponse): Seller {
        val id = if (product.seller!!.id!=null){
            product.seller.id
        }else{ 0 }
        val power_seller_status = if (product.seller!!.power_seller_status!=null){
            product.seller.power_seller_status
        }else{ "" }
        val car_dealer = if (product.seller!!.car_dealer!=null){
            product.seller.car_dealer
        }else{ false }
        val real_estate_agency = if (product.seller!!.real_estate_agency!=null){
            product.seller.real_estate_agency
        }else{ false }

        return Seller(id.toString(), power_seller_status!!, car_dealer!!, real_estate_agency!!)
    }

}