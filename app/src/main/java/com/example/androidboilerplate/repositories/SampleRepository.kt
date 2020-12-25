package com.example.androidboilerplate.repositories

import com.example.androidboilerplate.network.SampleDataSource
import kotlinx.coroutines.flow.map
import kotlin.text.Typography.ellipsis

class SampleRepository(
    private val sampleDataSource: SampleDataSource,
) {

    fun getLatestSamplesAbbrTitle(nChars: Int) =
        sampleDataSource.latestSamples.map { sample ->
            val abbrTitle = sample.title.take(nChars)
            if (sample.title == abbrTitle) return@map sample
            sample.copy(title = abbrTitle.trimEnd() + ellipsis)
        }
}