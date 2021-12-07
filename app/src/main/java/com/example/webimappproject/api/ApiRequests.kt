package com.example.webimappproject.api

import com.example.webimappproject.api.TicketsJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {

    @GET("/get?token=9876543210secret")
    suspend fun getTickets(): TicketsJson
}