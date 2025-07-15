package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.ui.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>
}