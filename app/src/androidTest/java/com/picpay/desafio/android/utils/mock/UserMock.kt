package com.picpay.desafio.android.utils.mock

import com.picpay.desafio.android.ui.model.User

object UserMock {

    val user = User(
        username = "username",
        img = "https://randomuser.me/api/portraits/men/9.jpg",
        name = "Name",
        id = 1,
    )

    fun users() = listOf(user, user.copy(id = 2))
}