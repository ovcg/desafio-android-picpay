package com.picpay.desafio.android.data.source.local

import com.picpay.desafio.android.ui.User

interface UserLocalDataSource {

    suspend fun getUsers(): List<User>

    suspend fun saveUsers(users: List<User>)

    suspend fun deleteAll()
}