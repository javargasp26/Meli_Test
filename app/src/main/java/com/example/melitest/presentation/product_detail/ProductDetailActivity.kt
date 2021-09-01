package com.example.melitest.presentation.product_detail

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.melitest.R
import com.example.melitest.databinding.ActivityProductDetailBinding
import com.example.melitest.databinding.ActivityProductDetailLandscapeBinding
import com.example.melitest.databinding.ActivityProductListBinding
import com.example.melitest.presentation.model.Product

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productSelected: Product
    private lateinit var bindingProductDetailActivity: ActivityProductDetailBinding
    private lateinit var bindingProductDetailLandscapeActivity: ActivityProductDetailLandscapeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPortraitView()
    }

    private fun setPortraitView() {
        setContentView(R.layout.activity_product_detail)
        bindingProductDetailActivity = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(bindingProductDetailActivity.root)
        productSelected = intent.getParcelableExtra("product")!!

        bindImage(bindingProductDetailActivity.imgProductPic, productSelected.thumbnail)
        bindingProductDetailActivity.tvProductName.text = productSelected.title
        val priceMessage = "$ "+productSelected.price.toString()
        bindingProductDetailActivity.tvProductPrice.text = priceMessage
    }

    private fun setLandscapeView() {
        setContentView(R.layout.activity_product_detail_landscape)
        bindingProductDetailLandscapeActivity = ActivityProductDetailLandscapeBinding.inflate(layoutInflater)
        setContentView(bindingProductDetailLandscapeActivity.root)
        productSelected = intent.getParcelableExtra("product")!!

        bindImage(bindingProductDetailLandscapeActivity.imgProductPic, productSelected.thumbnail)
        bindingProductDetailLandscapeActivity.tvProductName.text = productSelected.title
        val priceMessage = "$ "+productSelected.price.toString()
        bindingProductDetailLandscapeActivity.tvProductPrice.text = priceMessage
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri =
                imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            setLandscapeView()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            setPortraitView()
        }
    }
}