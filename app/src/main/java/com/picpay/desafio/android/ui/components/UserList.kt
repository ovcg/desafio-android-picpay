package com.picpay.desafio.android.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.ui.theme.UserAppTheme

@Composable
fun UserList(users: List<User>) {
    LazyColumn { items(users, { user -> user.id }) { user -> UserItem(user) } }
}

@Preview(showBackground = true)
@Composable
private fun UserListPreview() {
    UserAppTheme {
        UserList(
            listOf(
                User(
                    username = "username",
                    img = "https://randomuser.me/api/portraits/men/9.jpg",
                    name = "Eduardo Santos",
                    id = 1,
                ),
                User(
                    username = "username",
                    img = "https://randomuser.me/api/portraits/men/9.jpg",
                    name = "Eduardo Santos",
                    id = 2,
                )
            )
        )
    }
}
