package com.picpay.desafio.android.data

import com.picpay.desafio.android.ui.model.User
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}