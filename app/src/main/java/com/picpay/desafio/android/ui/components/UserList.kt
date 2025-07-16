package com.picpay.desafio.android.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.ui.theme.UserAppTheme

fun LazyListScope.UserList(users: List<User>, onReload: () -> Unit) {
    if (users.isEmpty()) {
        item {
            UserError(
                message = R.string.empty_list,
                icon = R.drawable.ic_round_account_circle,
                onReload,
            )
        }
    } else {
        items(users, { user -> user.id }) { user -> UserItem(user) }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserListPreview() {
    UserAppTheme {
        LazyColumn {
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
                    ),
                ),
                onReload = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyListPreview() {
    UserAppTheme { LazyColumn { UserList(emptyList(), {}) } }
}
