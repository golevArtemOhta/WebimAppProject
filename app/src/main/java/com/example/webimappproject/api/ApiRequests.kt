package com.example.webimappproject.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("/get")
    suspend fun getTickets(@Query("token") token: String): TicketsJson
}