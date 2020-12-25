package com.example.androidboilerplate.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.androidboilerplate.database.entities.Sample
import kotlinx.coroutines.flow.Flow


@Dao
abstract class SampleDao : BaseDao<Sample>() {
    @Query("SELECT * FROM samples")
    abstract fun getAll(): Flow<List<Sample>>
}