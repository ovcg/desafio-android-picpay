package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.ui.model.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            val result = repository.getUsers()
            when {
                result.isSuccess -> {
                    _userState.update {
                        val users = result.getOrNull()
                        UserState.Success(users ?: emptyList())
                    }
                }
                else -> {
                    _userState.update { UserState.Error }
                }
            }
        }
    }
}
