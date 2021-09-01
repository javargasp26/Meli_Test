package com.example.melitest.presentation.product_list.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melitest.databinding.RowProductItemBinding
import com.example.melitest.presentation.model.Product

class ProductViewHolder(itemView: View, private val context: Context, private val cellClickListener: CellClickListener) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = RowProductItemBinding.bind(itemView)
    fun bind(product: Product) {
        binding.tvProductName.text = product.title
        val priceMessage = "$ "+product.price.toString()
        binding.tvProductPrice.text = priceMessage

        bindImage(binding.imgProductPic, product.thumbnail)

        binding.linearLayout.setOnClickListener {
            cellClickListener.onCellClickListener(product)
        }

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
}

interface CellClickListener {
    fun onCellClickListener(product: Product)
}