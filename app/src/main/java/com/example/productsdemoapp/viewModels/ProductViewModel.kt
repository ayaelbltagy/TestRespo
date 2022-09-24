package com.example.productsdemoapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productsdemoapp.database.ProductDB.Companion.getInstance
import com.example.productsdemoapp.database.ProductEntity
import com.example.productsdemoapp.repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getInstance(application)

    private val repo = ProductsRepository(database)

    private val list = repo.listOfProducts

    private var emptyList = mutableListOf<ProductEntity>() // empty list will adding it when click on book mark

    val showedList: MediatorLiveData<List<ProductEntity>> = MediatorLiveData() // used for showing whatever on it

    private var _selectedListLiveData = MutableLiveData<List<ProductEntity>>() // to add book mark list to showedList


    init {
        getListOfProducts()
        viewModelScope.launch {
            showedList.addSource(list) {
                showedList.value = it
            }

        }
    }

    private fun getListOfProducts() {
        viewModelScope.launch {
            repo.refreshedProducts()
        }
    }

    fun saveThisProduct(pro: ProductEntity) {
        removeSource()
        emptyList.add(pro)
        _selectedListLiveData.value = emptyList
        showedList.addSource(_selectedListLiveData) {
            showedList.value = it

        }
    }

    private fun removeSource() {
        showedList.removeSource(list)
    }

}