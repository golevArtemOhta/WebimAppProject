package com.example.webimappproject.apiLogin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequestsLogin {

    @GET("/auth")
    fun getData(
        @Query("password") password: String,
        @Query("login") login: String
    ): Call<LoginJson>
}