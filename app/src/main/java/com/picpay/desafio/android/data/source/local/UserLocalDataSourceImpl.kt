package com.picpay.desafio.android.data.source.local

import com.picpay.desafio.android.data.common.dao.UserDao
import com.picpay.desafio.android.data.common.entity.toModel
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.ui.model.toEntity

class UserLocalDataSourceImpl(val userDao: UserDao) : UserLocalDataSource {

    override suspend fun getUsers(): List<User> {
        return userDao.getAll().toModel()
    }

    override suspend fun saveUsers(users: List<User>) {
        userDao.deleteAndInsertTransaction(users.map { it.toEntity() })
    }
}