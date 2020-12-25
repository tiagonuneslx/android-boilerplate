package com.example.androidboilerplate.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidboilerplate.database.dao.SampleDao
import com.example.androidboilerplate.database.entities.Sample

@Database(
    entities = [
        Sample::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val sampleDao: SampleDao
}