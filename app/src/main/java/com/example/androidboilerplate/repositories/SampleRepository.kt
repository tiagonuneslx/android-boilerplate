package com.example.androidboilerplate.repositories

import com.example.androidboilerplate.network.SampleDataSource
import kotlinx.coroutines.flow.filter

class SampleRepository(
    private val sampleDataSource: SampleDataSource,
) {
    fun getFirstNSamples(n: Int) =
        sampleDataSource.latestSamples.filter { it.id <= n }
}