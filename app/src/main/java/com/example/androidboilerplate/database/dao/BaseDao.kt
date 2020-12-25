package com.example.androidboilerplate.database.dao

import androidx.room.*


@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entities: List<T>): List<Long>

    @Update
    fun update(entity: T)

    @Update
    fun update(entities: List<T>)

    @Delete
    fun delete(entity: T)

    @Delete
    fun delete(entities: List<T>)

    @Transaction
    fun upsert(entity: T) {
        val id = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }

    @Transaction
    fun upsert(entities: List<T>): List<T> {
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