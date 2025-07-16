package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.picpay.desafio.android.ui.theme.UserAppTheme
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.userState.collectAsState()
            UserAppTheme { UserScreen(userState = state) { viewModel.getUsers() } }
        }
    }
}
