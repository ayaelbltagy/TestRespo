package com.example.productsdemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllProducts (product : List<ProductEntity>)

    @Query("SELECT * FROM product_table")
    fun getAllProducts () : LiveData<List<ProductEntity>>

}