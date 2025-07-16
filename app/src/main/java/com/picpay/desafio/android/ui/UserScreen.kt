package com.picpay.desafio.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.components.UserError
import com.picpay.desafio.android.ui.components.UserList
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.ui.model.UserState
import com.picpay.desafio.android.ui.theme.Accent
import com.picpay.desafio.android.ui.theme.PrimaryDark
import com.picpay.desafio.android.ui.theme.Typography
import com.picpay.desafio.android.ui.theme.UserAppTheme

@Composable
fun UserScreen(userState: UserState?, onReload: () -> Unit = {}) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize().background(PrimaryDark)) {
        item {
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 48.dp),
                text = stringResource(R.string.title),
                style = Typography.titleLarge,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        when (userState) {
            is UserState.Loading -> {
                 Loading()
            }

            is UserState.Success -> {
                UserList(userState.users, onReload)
            }

            else -> {
                item { UserError(onReload = onReload) }
            }
        }
    }
}

private fun LazyListScope.Loading() {
    item {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.wrapContentSize().align(Alignment.Center)) {
                CircularProgressIndicator(
                    color = Accent,
                    modifier = Modifier.wrapContentSize().padding(8.dp).align(Alignment.TopCenter),
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserScreenLoadingPreview() {
    UserAppTheme { UserScreen(UserState.Loading) }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserScreenPreview() {
    UserAppTheme {
        UserScreen(
            UserState.Success(listOf(User(id = 1, img = "", name = "nome", username = "username")))
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserScreenEmptyPreview() {
    UserAppTheme { UserScreen(UserState.Success(emptyList())) }
}
