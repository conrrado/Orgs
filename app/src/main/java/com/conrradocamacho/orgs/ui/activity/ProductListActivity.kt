package com.conrradocamacho.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.database.AppDatabase
import com.conrradocamacho.orgs.databinding.ActivityProductListBinding
import com.conrradocamacho.orgs.model.Product
import com.conrradocamacho.orgs.ui.recyclerview.adapter.ProductListAdapter

class ProductListActivity: AppCompatActivity(R.layout.activity_product_list) {

    companion object {
        private val TAG = this::class.simpleName
    }

    private val adapter = ProductListAdapter()
    private val binding by lazy { ActivityProductListBinding.inflate(layoutInflater) }
    private val productDao by lazy { AppDatabase.getInstance(this).productDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
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
            putExtra(PRODUCT_ID_KEY, product.id)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_sort_by_name_desc -> {
                adapter.update(productDao.getAllOrderByNameDesc())
            }
            R.id.item_sort_by_name_asc -> {
                adapter.update(productDao.getAllOrderByNameAsc())
            }
            R.id.item_sort_by_description_desc -> {
                adapter.update(productDao.getAllOrderByDescriptionDesc())
            }
            R.id.item_sort_by_description_asc -> {
                adapter.update(productDao.getAllOrderByDescriptionAsc())
            }
            R.id.item_sort_by_price_desc -> {
                adapter.update(productDao.getAllOrderByPriceDesc())
            }
            R.id.item_sort_by_price_asc -> {
                adapter.update(productDao.getAllOrderByPriceAsc())
            }
            R.id.item_without_sort -> {
                adapter.update(productDao.getAll())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}