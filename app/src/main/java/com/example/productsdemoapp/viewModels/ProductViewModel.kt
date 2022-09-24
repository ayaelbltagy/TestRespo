package com.example.productsdemoapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsdemoapp.database.ProductDB.Companion.getInstance
import com.example.productsdemoapp.repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getInstance(application)

    private val repo = ProductsRepository(database)

      val list = repo.listOfProducts


    init {
        getListOfDay()
    }


    private fun getListOfDay() {
        viewModelScope.launch {
            repo.refreshedProducts()
        }
    }

}