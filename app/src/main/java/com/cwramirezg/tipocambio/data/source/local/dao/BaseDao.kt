package com.cwramirezg.tipocambio.data.source.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<T>): Single<List<Long>>

    @Update
    fun update(entity: List<T>): Completable

    @Update
    fun update(entity: T): Completable

    @Delete
    fun delete(entity: T): Single<Int>

}
