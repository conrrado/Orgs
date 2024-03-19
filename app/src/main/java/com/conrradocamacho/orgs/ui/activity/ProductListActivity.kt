package com.conrradocamacho.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.dao.ProductDAO
import com.conrradocamacho.orgs.ui.recyclerview.adapter.ProductListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductListActivity: AppCompatActivity(R.layout.activity_product_list) {

    private val dao = ProductDAO()
    private val adapter = ProductListAdapter(dao.searchAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchAll())

    }

    private fun configRecyclerView() {
        val recyclerViewProduct = findViewById<RecyclerView>(R.id.product_list_recycler_view)
        recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        recyclerViewProduct.adapter = adapter
    }

    private fun configFab() {
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.product_list_floating_action_button)
        floatingActionButton.setOnClickListener {
            callProductForm()
        }
    }

    private fun callProductForm() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }
}