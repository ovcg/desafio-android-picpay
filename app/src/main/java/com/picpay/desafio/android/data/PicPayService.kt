package com.picpay.desafio.android.data

import com.picpay.desafio.android.ui.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    fun getUsers(): List<User>
}