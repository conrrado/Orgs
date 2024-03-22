package com.conrradocamacho.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.dao.ProductDAO
import com.conrradocamacho.orgs.databinding.ActivityProductListBinding
import com.conrradocamacho.orgs.ui.recyclerview.adapter.ProductListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

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