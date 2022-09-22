package com.example.productsdemoapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.productsdemoapp.apis.APIService
import com.example.productsdemoapp.database.ProductDB.Companion.getInstance
import com.example.productsdemoapp.repository.ProductsRepository
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val database = getInstance(application)

         val repo = ProductsRepository(database)

         runBlocking {
             if (repo != null) {
                 Toast.makeText(this@MainActivity,repo.refreshedProducts().toString(),Toast.LENGTH_LONG).show()
             }


        }
    }
}