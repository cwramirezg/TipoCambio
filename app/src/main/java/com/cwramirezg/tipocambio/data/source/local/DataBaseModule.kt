package com.cwramirezg.tipocambio.data.source.local

import android.content.Context
import androidx.room.Room
import com.cwramirezg.tipocambio.BuildConfig
import org.koin.dsl.module

val dataBaseModule = module {
    factory { getRoom(get()) }
}

fun getRoom(context: Context): Database {
    return Room.databaseBuilder(context, Database::class.java, BuildConfig.DB_NAME)
        .createFromAsset("database/app.db")
//        .addMigrations(
//            MigrationHelper.MIGRATION_1_2
//        )
        .fallbackToDestructiveMigrationOnDowngrade()
        //.addCallback(RoomCallback())
        .build()
}