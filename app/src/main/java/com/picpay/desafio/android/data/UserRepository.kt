package com.picpay.desafio.android.data

import com.picpay.desafio.android.ui.User

interface UserRepository {

    suspend fun getUsers(): List<User>
}