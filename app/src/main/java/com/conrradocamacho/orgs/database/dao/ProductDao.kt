package com.conrradocamacho.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.conrradocamacho.orgs.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product ORDER BY name DESC")
    fun getAllOrderByNameDesc(): List<Product>

    @Query("SELECT * FROM product ORDER BY name ASC")
    fun getAllOrderByNameAsc(): List<Product>

    @Query("SELECT * FROM product ORDER BY description DESC")
    fun getAllOrderByDescriptionDesc(): List<Product>

    @Query("SELECT * FROM product ORDER BY description ASC")
    fun getAllOrderByDescriptionAsc(): List<Product>

    @Query("SELECT * FROM product ORDER BY price DESC")
    fun getAllOrderByPriceDesc(): List<Product>

    @Query("SELECT * FROM product ORDER BY price ASC")
    fun getAllOrderByPriceAsc(): List<Product>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getById(id: Long): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg product: Product)

    @Delete
    fun delete(product: Product)

    @Update
    fun update(product: Product)
}