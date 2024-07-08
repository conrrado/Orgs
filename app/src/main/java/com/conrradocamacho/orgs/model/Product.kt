package com.conrradocamacho.orgs.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val name: String,
    val description: String,
    val price: BigDecimal,
    val image: String? = null
)
