package com.example.melitest.presentation.product_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.melitest.R
import com.example.melitest.presentation.model.Product

class ProductAdapter(productList: List<Product>, private val cellClickListener: CellClickListener) : RecyclerView.Adapter<ProductViewHolder>() {

    private val productList = mutableListOf<Product>()
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_product_item, parent, false)
        this.context = parent.context
        return ProductViewHolder(view, context, cellClickListener)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun setProductList(productList: List<Product>) {
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }
}
