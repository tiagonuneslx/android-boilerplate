package com.example.androidboilerplate.repositories

import com.example.androidboilerplate.database.dao.SampleDao
import com.example.androidboilerplate.database.entities.Sample
import com.example.androidboilerplate.network.SampleDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlin.text.Typography.ellipsis

class SampleRepository(
    private val sampleDataSource: SampleDataSource,
    private val sampleDao: SampleDao,
) {

    private suspend fun saveInCache(samples: List<Sample>) = withContext(Dispatchers.IO) {
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
            .catch { exception ->
                exception.printStackTrace()
                emitAll(sampleDao.getAll())
            }
}