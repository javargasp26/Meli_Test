package com.example.melitest.presentation.product_search

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.melitest.R
import com.example.melitest.data.network.retrofit.MeliApi

import com.example.melitest.databinding.ActivitySearchBinding
import com.example.melitest.databinding.ActivitySearchLandscapeBinding
import com.example.melitest.presentation.model.Product
import com.example.melitest.presentation.product_list.ProductListActivity
import com.example.melitest.presentation.util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    private lateinit var bindingSearchActivity: ActivitySearchBinding
    private lateinit var bindingSearchLandscapeActivity: ActivitySearchLandscapeBinding

    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPortraitView("")
    }

    private fun setonClickListener() {
        bindingSearchActivity.imgSearch.setOnClickListener {
            val productText = bindingSearchActivity.edtSearch.text.toString()
            if (productText!="" && productText.length>2){
                hideKeyboard()
                showLoading()
                searchByName(productText)
            }else{
                Toast.makeText(context, "Escribe en el buscador lo que quieres encontrar. Escribe al menos tres caracteres", Toast.LENGTH_SHORT).show()
            }
        }
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
                    val productsArrayList = productList.toCollection(ArrayList())

                    val intent = Intent(context, ProductListActivity::class.java)
                    intent.putExtra("productsArrayList", productsArrayList)
                    intent.putExtra("query", query)
                    startActivity(intent)
                }else{
                    Toast.makeText(context, "Error descargando", Toast.LENGTH_SHORT).show()
                    hideLoading()
                }
            }

        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(bindingSearchActivity.root.windowToken, 0)
    }

    fun showLoading(){

        bindingSearchActivity.layoutLoadingMainActivity.root.visibility = View.VISIBLE
    }

    fun hideLoading(){
        bindingSearchActivity.layoutLoadingMainActivity.root.visibility = View.GONE
    }

    fun showLoadingLandscape(){

        bindingSearchLandscapeActivity.layoutLoadingMainActivity.root.visibility = View.VISIBLE
    }

    fun hideLoadingLandscape(){
        bindingSearchLandscapeActivity.layoutLoadingMainActivity.root.visibility = View.GONE
    }

    fun setPortraitView(query: String) {
        setContentView(R.layout.activity_search)
        context = this
        bindingSearchActivity = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(bindingSearchActivity.root)
        if (query!=""){
            bindingSearchActivity.edtSearch.setText(query)
        }
        setonClickListener()
    }

    fun setLandscapeView(query: String) {
        setContentView(R.layout.activity_search_landscape)
        context = this
        bindingSearchLandscapeActivity = ActivitySearchLandscapeBinding.inflate(layoutInflater)
        setContentView(bindingSearchLandscapeActivity.root)
        if (query!=""){
            bindingSearchLandscapeActivity.edtSearch.setText(query)
        }
        setonClickListenerLandscape()
    }

    private fun setonClickListenerLandscape() {
        bindingSearchLandscapeActivity.imgSearch.setOnClickListener {
            val productText = bindingSearchLandscapeActivity.edtSearch.text.toString()
            if (productText!="" && productText.length>2){
                hideKeyboardLandscape()
                showLoadingLandscape()
                searchByNameLandscape(productText)
            }else{
                Toast.makeText(context, "Escribe en el buscador lo que quieres encontrar. Escribe al menos tres caracteres", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun searchByNameLandscape(query:String){
        val productList = mutableListOf<Product>()

        CoroutineScope(Dispatchers.IO).launch {
            val call  = MeliApi.create().getProductsBySearch(query)

            runOnUiThread {
                if(call.isSuccessful){
                    hideLoadingLandscape()
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
                    val productsArrayList = productList.toCollection(ArrayList())

                    val intent = Intent(context, ProductListActivity::class.java)
                    intent.putExtra("productsArrayList", productsArrayList)
                    intent.putExtra("query", query)
                    startActivity(intent)
                }else{
                    Toast.makeText(context, "Error descargando", Toast.LENGTH_SHORT).show()
                    hideLoadingLandscape()
                }
            }

        }
    }

    private fun hideKeyboardLandscape() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(bindingSearchLandscapeActivity.root.windowToken, 0)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        var query=""
        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            query = bindingSearchActivity.edtSearch.text.toString()
            setLandscapeView(query)
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            query = bindingSearchLandscapeActivity.edtSearch.text.toString()
            setPortraitView(query)
        }
    }
}



