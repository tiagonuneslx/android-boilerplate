package com.example.androidboilerplate.network.api

import com.example.androidboilerplate.database.entities.Sample
import retrofit2.http.GET

interface SampleApi {
    @GET("photos")
    suspend fun getAllSamples(): List<Sample>
}