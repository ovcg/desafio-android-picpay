package com.picpay.desafio.android.data.source.network

import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.ui.model.User

class UserRemoteDataSourceImpl(private val service: PicPayService) : UserRemoteDataSource {
    override suspend fun getUsers(): List<User> {
        return service.getUsers()
    }
}