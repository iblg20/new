package com.example.catapif.api

import com.example.catapif.model.CatDataItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    private val service: ApiInterface
    private val BASE_URL = "https://api.thecatapi.com/v1/images/"

    init{
        val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        service = api.create(ApiInterface::class.java)
    }

    suspend fun getMyData(): List<CatDataItem> {
        return service.getData()
    }
}