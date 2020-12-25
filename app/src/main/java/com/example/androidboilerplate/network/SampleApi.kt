package com.example.androidboilerplate.network

import com.example.androidboilerplate.network.responses.GetAllSamplesResponse
import retrofit2.http.GET

interface SampleApi {
    @GET
    suspend fun getAllSamples(): GetAllSamplesResponse
}