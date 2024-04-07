package com.conrradocamacho.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.dao.ProductDAO
import com.conrradocamacho.orgs.databinding.ActivityProductListBinding
import com.conrradocamacho.orgs.model.Product
import com.conrradocamacho.orgs.ui.recyclerview.adapter.ProductListAdapter

class ProductListActivity: AppCompatActivity(R.layout.activity_product_list) {

    private val dao = ProductDAO()
    private val adapter = ProductListAdapter(dao.searchAll())
    private val binding by lazy { ActivityProductListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchAll())

    }

    private fun configRecyclerView() {
        val recyclerViewProduct = binding.productListRecyclerView
        recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        recyclerViewProduct.adapter = adapter
        adapter.onClickProductItem = {
            Log.i(ProductListActivity::class.simpleName, "click on item: ${it.name}")
            openDetailProduct(it)
        }
    }

    private fun openDetailProduct(product: Product) {
        val intent = Intent(baseContext, DetailProductActivity::class.java).apply {
            putExtra("product", product)
        }
        startActivity(intent)
    }

    private fun configFab() {
        val floatingActionButton = binding.productListFloatingActionButton
        floatingActionButton.setOnClickListener {
            callProductForm()
        }
    }

    private fun callProductForm() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }
}