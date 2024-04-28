package com.conrradocamacho.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.conrradocamacho.orgs.database.converter.Converters
import com.conrradocamacho.orgs.database.dao.ProductDao
import com.conrradocamacho.orgs.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}