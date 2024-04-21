package com.conrradocamacho.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.conrradocamacho.orgs.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun findAll(): List<Product>

    @Insert
    fun save(product: Product)
}