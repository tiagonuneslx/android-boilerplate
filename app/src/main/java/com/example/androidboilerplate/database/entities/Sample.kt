package com.example.androidboilerplate.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "samples")
data class Sample(
    @PrimaryKey val id: Long,
    val title: String,
    val url: String,
)