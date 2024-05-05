package com.conrradocamacho.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.database.AppDatabase
import com.conrradocamacho.orgs.databinding.ActivityProductListBinding
import com.conrradocamacho.orgs.model.Product
import com.conrradocamacho.orgs.ui.recyclerview.adapter.ProductListAdapter

class ProductListActivity: AppCompatActivity(R.layout.activity_product_list) {

    private val adapter = ProductListAdapter()
    private val binding by lazy { ActivityProductListBinding.inflate(layoutInflater) }
    private val TAG = this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        adapter.update(productDao.getAll())
    }

    private fun configRecyclerView() {
        val recyclerViewProduct = binding.productListRecyclerView
        recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        recyclerViewProduct.adapter = adapter
        adapter.onClickProductItem = {
            Log.i(TAG, "click on item: ${it.name}")
            openDetailProduct(it)
        }
        adapter.onMenuProductEdit = {
            Log.i(TAG, "click on menu edit, product id: ${it.id}")
        }
        adapter.onMenuProductDelete = {
            Log.i(TAG, "click on menu delete, product id: ${it.id}")
        }
    }

    private fun openDetailProduct(product: Product) {
        val intent = Intent(baseContext, DetailProductActivity::class.java).apply {
            putExtra(DetailProductActivity.product_id_key, product.id)
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