package com.example.catapif.api

import com.example.catapif.model.CatDataItem
import com.example.catapif.model.Vote
import com.example.catapif.model.VoteResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("images/search?limit=20&page=10&order=Desc")
    suspend fun getData(): List<CatDataItem>

    @POST("votes")
    @Headers("Content-Type:application/json", "x-api-key: e1add36b-516e-4482-a788-494bf026c32c")
    fun sendData(@Body vote: Vote): Call<VoteResponse>
}