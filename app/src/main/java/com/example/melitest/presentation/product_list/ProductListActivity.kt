package com.example.melitest.presentation.product_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.melitest.R
import com.example.melitest.data.network.retrofit.MeliApi
import com.example.melitest.databinding.ActivityProductListBinding
import com.example.melitest.presentation.model.Product
import com.example.melitest.presentation.product_detail.ProductDetailActivity
import com.example.melitest.presentation.product_list.adapter.CellClickListener
import com.example.melitest.presentation.product_list.adapter.ProductAdapter
import com.example.melitest.presentation.util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity(), CellClickListener {

    private var productList: List<Product> = mutableListOf()
    private lateinit var bindingProductListActivity: ActivityProductListBinding
    private lateinit var productAdapter: ProductAdapter
    lateinit var rcvProductList : RecyclerView

    var query = ""
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        bindingProductListActivity = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(bindingProductListActivity.root)

        context = this
        productList = intent.getParcelableArrayListExtra<Product>("productsArrayList")!!
        query = intent.getStringExtra("query")!!
        rcvProductList = bindingProductListActivity.rcvProductList

        bindingProductListActivity.edtSearch.setText(query)
        setUpRecyclerView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        bindingProductListActivity.imgSearch.setOnClickListener {
            val productText = bindingProductListActivity.edtSearch.text.toString()
            if (productText!="" && productText.length>2){
                hideKeyboard()
                showLoading()
                searchByName(productText)
            }else{
                Toast.makeText(context, "Escribe en el buscador lo que quieres encontrar. Escribe al menos tres caracteres", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(bindingProductListActivity.root.windowToken, 0)
    }

    fun setUpRecyclerView(){

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rcvProductList.layoutManager = layoutManager
        productAdapter = ProductAdapter(productList, this)
        rcvProductList.adapter = productAdapter
        productAdapter.setProductList(productList)
    }

    override fun onCellClickListener(product: Product) {
        query =  bindingProductListActivity.edtSearch.text.toString()

        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("product", product)
        intent.putExtra("query", query)

        startActivity(intent)
    }

    private fun searchByName(query:String){
        val productList = mutableListOf<Product>()

        CoroutineScope(Dispatchers.IO).launch {
            val call  = MeliApi.create().getProductsBySearch(query)

            runOnUiThread {
                if(call.isSuccessful){
                    hideLoading()
                    if (call.body()!!.results!!.isNotEmpty()){
                        call.body()!!.results!!.forEach { product->
                            product.seller
                            productList.add(
                                Product(
                                    product.id!!,
                                    product.title!!,
                                    util().getSeller(product),
                                    product.price!!,
                                    product.currency_id!!,
                                    product.thumbnail!!,
                                    product.tags!!
                                )
                            )
                        }
                    }
                    productAdapter.setProductList(productList)
                }else{
                    Toast.makeText(context, "Error descargando", Toast.LENGTH_SHORT).show()
                    hideLoading()
                }
            }
        }
    }

    fun showLoading(){

        bindingProductListActivity.layoutLoadingMainActivity.root.visibility = View.VISIBLE
    }

    fun hideLoading(){
        bindingProductListActivity.layoutLoadingMainActivity.root.visibility = View.GONE
    }

}