package com.picpay.desafio.android.data.common.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.picpay.desafio.android.data.common.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    suspend fun getAll(): List<UserEntity>

    @Query("DELETE FROM Users")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<UserEntity>)

    @Transaction
    suspend fun deleteAndInsertTransaction(newUsers: List<UserEntity>) {
        deleteAll()
        insert(newUsers)
    }
}
