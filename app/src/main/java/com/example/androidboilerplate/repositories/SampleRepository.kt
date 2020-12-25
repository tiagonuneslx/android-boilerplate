package com.example.androidboilerplate.repositories

import com.example.androidboilerplate.database.dao.SampleDao
import com.example.androidboilerplate.database.entities.Sample
import com.example.androidboilerplate.network.SampleDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlin.text.Typography.ellipsis

class SampleRepository(
    private val sampleDataSource: SampleDataSource,
    private val sampleDao: SampleDao,
) {

    private fun saveInCache(samples: List<Sample>) {
        sampleDao.upsert(samples)
    }

    fun getLatestSamplesAbbrTitle(nChars: Int) =
        sampleDataSource.latestSamples
            .map mapFlow@{ latestSamples ->
                latestSamples.map mapSamples@{ sample ->
                    val abbrTitle = sample.title.take(nChars)
                    if (sample.title == abbrTitle) return@mapSamples sample
                    sample.copy(title = abbrTitle.trimEnd() + ellipsis)
                }
            }
            .onEach { latestSamples -> saveInCache(latestSamples) }
            .catch { emitAll(sampleDao.getAll()) }
}