package com.conrradocamacho.orgs.dao

import com.conrradocamacho.orgs.model.Product
import java.math.BigDecimal

class ProductDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun searchAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                name = "Salada de frutas",
                description = "Maça e uva",
                price = BigDecimal("19.98")
            )
        )
    }
}