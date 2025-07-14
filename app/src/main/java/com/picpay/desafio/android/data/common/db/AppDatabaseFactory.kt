package com.picpay.desafio.android.data.common.db

import android.content.Context
import androidx.room.Room

internal object AppDatabaseFactory {

    fun build(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.FILE_NAME).build()
    }
}