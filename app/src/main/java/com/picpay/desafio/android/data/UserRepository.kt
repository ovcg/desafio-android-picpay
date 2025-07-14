package com.picpay.desafio.android.data

import com.picpay.desafio.android.ui.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>
}