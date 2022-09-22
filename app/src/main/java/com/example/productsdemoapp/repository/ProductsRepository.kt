package com.example.productsdemoapp.repository

import com.example.productsdemoapp.apis.APIService
import com.example.productsdemoapp.database.ProductDB
import com.example.productsdemoapp.database.ProductEntity
import com.example.productsdemoapp.models.ConvertedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRepository(private val database: ProductDB) {

    suspend fun refreshedProducts() {
        withContext(Dispatchers.IO) {
            val allPro = APIService.getProducts()
            database.getProDao().addAllProducts(allPro)

        }
    }


    fun List<ConvertedModel>.ModelToEntitiy(): List<ProductEntity> {
        return map {
            ProductEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                prepare_time = it.prepare_time,
            )
        }
    }
}