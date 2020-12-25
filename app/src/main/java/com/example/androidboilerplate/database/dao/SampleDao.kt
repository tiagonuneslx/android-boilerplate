package com.example.androidboilerplate.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.androidboilerplate.database.entities.Sample
import kotlinx.coroutines.flow.Flow


@Dao
interface SampleDao : BaseDao<Sample> {
    @Query("SELECT * FROM samples")
    fun getAll(): Flow<List<Sample>>
}