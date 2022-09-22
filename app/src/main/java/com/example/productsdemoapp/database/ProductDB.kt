package com.example.productsdemoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class],version =1,exportSchema = false)

abstract class ProductDB : RoomDatabase() {

    // to access database you should get from DAO
    abstract fun getProDao(): ProductDao


    // to prevent more connection should use one instance from database so make single tone design pattern
    companion object {

        @Volatile
        private var INSTANCE: ProductDB? = null

        // function to get object from database class
        fun getInstance(context: Context): ProductDB {

            // to block code until this instance created
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDB::class.java,"PRODUCT_DATA_BASE"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
