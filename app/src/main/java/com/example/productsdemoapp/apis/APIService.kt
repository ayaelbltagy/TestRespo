package com.example.productsdemoapp.apis

import com.example.productsdemoapp.apis.APIService.ServerApi.retrofitService
import com.example.productsdemoapp.database.ProductEntity
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object APIService {

    private const val BASE_URL = "http://144.91.100.164:9090/client/"

    // moshi is a lib used to convert json response to string
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    object ServerApi {
        val retrofitService: ProductAPI = retrofit.create(ProductAPI::class.java)
    }


    suspend fun getProducts(): List<ProductEntity> {
        val responseStr = retrofitService.getProducts("30.063903808594", "31.326583983165", "1", "y")
        val responseJsonObject = JSONObject(responseStr)
        val dataArrayList: ArrayList<ProductEntity> = ArrayList()
        val jsonArray = responseJsonObject.getJSONArray("data")
        for (i in 0 until jsonArray.length()) {
            val model: ProductEntity =
                Gson().fromJson(jsonArray.get(i).toString(), ProductEntity::class.java)
            dataArrayList.add(model)
        }
        return dataArrayList.toList()
    }

}