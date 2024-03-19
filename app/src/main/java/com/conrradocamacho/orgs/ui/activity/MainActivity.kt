package com.conrradocamacho.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.dao.ProductDAO
import com.conrradocamacho.orgs.model.Product
import com.conrradocamacho.orgs.ui.recyclerview.adapter.ProductListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity: AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val recyclerViewProduct = findViewById<RecyclerView>(R.id.recyclerViewProduct)
        val dao = ProductDAO()
        recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        recyclerViewProduct.adapter = ProductListAdapter(dao.searchAll())

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, FormProductActivity::class.java)
            startActivity(intent)
        }
    }
}