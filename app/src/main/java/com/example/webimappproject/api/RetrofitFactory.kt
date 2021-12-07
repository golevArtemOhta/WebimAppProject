package com.example.webimappproject.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun new() = Retrofit.Builder()
    .baseUrl("https://drsoak.pythonanywhere.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiRequests::class.java)
}