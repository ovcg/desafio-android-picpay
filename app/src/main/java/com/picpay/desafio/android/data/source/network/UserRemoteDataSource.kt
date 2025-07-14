package com.picpay.desafio.android.data.source.network

import com.picpay.desafio.android.ui.model.User

fun interface UserRemoteDataSource {
    suspend fun getUsers(): List<User>
}