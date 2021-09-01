package com.example.melitest.data.network.retrofit

import com.example.melitest.data.network.product.model.ProductResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliApi {


    @GET("search")
    suspend fun getProductsBySearch(
        @Query("q") product:String): Response<ProductResponseBody>

    companion object {

        var BASE_URL = "https://api.mercadolibre.com/sites/MLA/"

        fun create() : MeliApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(MeliApi::class.java)

        }
    }
}