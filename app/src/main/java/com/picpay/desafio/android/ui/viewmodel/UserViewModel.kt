package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.ui.model.UserState
import kotlinx.coroutines.flow.MutableStateFlow
 import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: LiveData<UserState> = _userState.asLiveData()

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
                        UserState.Success(users?: emptyList())
                    }
                }
                else -> {
                    _userState.update {
                        UserState.Error
                    }
                }
            }
        }
    }
}