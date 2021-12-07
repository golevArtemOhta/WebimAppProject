package com.example.webimappproject.apiLogin

import com.example.webimappproject.api.TicketsJson
import com.example.webimappproject.apiLogin.LoginJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequestsLogin {

    @GET("/auth")
    fun getData(@Query("password") password: String,
        @Query("login") login: String): Call<LoginJson>
}