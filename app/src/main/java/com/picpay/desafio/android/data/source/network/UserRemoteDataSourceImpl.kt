package com.picpay.desafio.android.data.source.network

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.ui.User

class UserRemoteDataSourceImpl(private val service: PicPayService): UserRemoteDataSource {
    override suspend fun getUsers(): List<User> {
        return service.getUsers()
    }
}