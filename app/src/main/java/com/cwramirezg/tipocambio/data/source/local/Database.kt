package com.cwramirezg.tipocambio.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cwramirezg.tipocambio.data.model.TipoCambio
import com.cwramirezg.tipocambio.data.source.local.dao.TipoCambioDao

@Database(
    entities = [TipoCambio::class],
    version = 1
)
@TypeConverters(
    Converters::class
)
abstract class Database : RoomDatabase() {
    abstract fun tipoCambioDao(): TipoCambioDao
}