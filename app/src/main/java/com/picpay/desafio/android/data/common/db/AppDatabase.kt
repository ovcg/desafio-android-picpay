package com.picpay.desafio.android.data.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.common.dao.UserDao
import com.picpay.desafio.android.data.common.db.AppDatabase.Companion.VERSION
import com.picpay.desafio.android.data.common.entity.UserEntity

@Database(entities = [UserEntity::class], version = VERSION, exportSchema = true)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val FILE_NAME = "app_database"
        const val VERSION = 1
    }
}