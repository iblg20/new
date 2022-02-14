package com.example.catapif.api

import com.example.catapif.model.CatDataItem
import retrofit2.http.GET

interface ApiInterface {

    @GET("search?limit=20&page=10&order=Desc")
    suspend fun getData(): List<CatDataItem>
}