package com.picpay.desafio.android.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.UserRepository
import com.picpay.desafio.android.ui.model.User
import kotlinx.coroutines.flow.MutableStateFlow
 import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users.asLiveData()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            val users = repository.getUsers()
            _users.update { users }
        }
    }
}