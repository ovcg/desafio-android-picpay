package com.picpay.desafio.android.data.source.local

import com.picpay.desafio.android.ui.User

class UserLocalDataSourceImpl() : UserLocalDataSource {

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUsers(users: List<User>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}
