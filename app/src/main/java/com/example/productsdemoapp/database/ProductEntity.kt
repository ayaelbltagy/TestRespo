package com.example.productsdemoapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "product_table")
class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val price: Double,
    val prepare_time: Double
 )