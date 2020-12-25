package com.example.androidboilerplate.network.datasource

import com.example.androidboilerplate.network.api.SampleApi
import kotlinx.coroutines.flow.flow

class SampleDataSource(
    private val sampleApi: SampleApi,
//    private val refreshIntervalMs: Long = 5000L,
) {
    val latestSamples = flow {
//        while (true) {
        val latestSamples = sampleApi.getAllSamples()
        emit(latestSamples)
//            delay(refreshIntervalMs)
//        }
    }
}