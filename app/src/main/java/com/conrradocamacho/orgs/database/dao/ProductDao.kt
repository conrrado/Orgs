package com.conrradocamacho.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.conrradocamacho.orgs.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getById(id: Long): Product

    @Insert
    fun save(vararg product: Product)
}