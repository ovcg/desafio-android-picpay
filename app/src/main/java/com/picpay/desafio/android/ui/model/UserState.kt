package com.picpay.desafio.android.ui.model

sealed class UserState {

    data object Loading: UserState()

    data object Error: UserState()

    data class Success(val users: List<User>): UserState()
}