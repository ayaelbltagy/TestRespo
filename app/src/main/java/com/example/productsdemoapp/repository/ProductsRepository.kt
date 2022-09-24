package com.example.productsdemoapp.repository

import androidx.lifecycle.LiveData
import com.example.productsdemoapp.apis.APIService
import com.example.productsdemoapp.database.ProductDB
import com.example.productsdemoapp.database.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRepository(private val database: ProductDB) {

    suspend fun refreshedProducts() {
        // to check in case error happened when get data from server
        withContext(Dispatchers.IO) {
            try {
                // get refreshed data from server
                val allPro = APIService.getProducts()
                database.getProDao().addAllProducts(allPro)
            } catch (e: Exception) {
                // get from cashed data
                database.getProDao().getAllProducts()
            }
        }
    }

    val listOfProducts: LiveData<List<ProductEntity>> = database.getProDao().getAllProducts()


}