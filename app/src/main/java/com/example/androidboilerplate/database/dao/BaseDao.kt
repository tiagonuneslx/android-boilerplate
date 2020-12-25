package com.example.androidboilerplate.database.dao

import androidx.room.*


@Dao
abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entities: List<T>): List<Long>

    @Update
    abstract suspend fun update(entity: T)

    @Update
    abstract suspend fun update(entities: List<T>)

    @Delete
    abstract suspend fun delete(entity: T)

    @Delete
    abstract suspend fun delete(entities: List<T>)

    @Transaction
    open suspend fun upsert(entity: T) {
        val id = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }

    @Transaction
    open suspend fun upsert(entities: List<T>): List<T> {
        val insertResult: List<Long> = insert(entities)
        val updateList = mutableListOf<T>()
        for (i in insertResult.indices) {
            if (insertResult[i] == -1L) {
                updateList.add(entities[i])
            }
        }
        if (updateList.isNotEmpty()) {
            update(updateList)
        }
        return updateList
    }
}