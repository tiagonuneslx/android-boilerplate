package com.example.androidboilerplate.network

import com.example.androidboilerplate.models.Sample
import retrofit2.http.GET

interface SampleApi {
    @GET
    fun getAllSamples(): Sample
}