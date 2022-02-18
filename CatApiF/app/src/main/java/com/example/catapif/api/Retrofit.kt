package com.example.catapif.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val httpClient = OkHttpClient.Builder().addInterceptor(logging)

private val BASE_URL = "https://api.thecatapi.com/v1/"

val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(httpClient.build())
    .build()

object RetrofitApi {
    val service: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}