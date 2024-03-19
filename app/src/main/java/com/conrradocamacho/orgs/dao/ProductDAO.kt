package com.conrradocamacho.orgs.dao

import com.conrradocamacho.orgs.model.Product

class ProductDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun searchAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}