package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.model.Product
import com.conrradocamacho.orgs.ui.recyclerview.adapter.ProductListAdapter
import java.math.BigDecimal

class MainActivity: AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerViewProduct = findViewById<RecyclerView>(R.id.recyclerViewProduct)
        recyclerViewProduct.layoutManager = LinearLayoutManager(this)
        recyclerViewProduct.adapter = ProductListAdapter(listOf(
            Product(
                name = "Test",
                description = "test test",
                price = BigDecimal("19.99")
            ),
            Product(
                name = "Test 1",
                description = "test test 1",
                price = BigDecimal("29.99")
            ),
            Product(
                name = "Test 2",
                description = "test test 2",
                price = BigDecimal("39.99")
            ),
        ))
    }
}