package com.picpay.desafio.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.ui.theme.Accent
import com.picpay.desafio.android.ui.theme.ColorDetail
import com.picpay.desafio.android.ui.theme.PrimaryDark
import com.picpay.desafio.android.ui.theme.Typography
import com.picpay.desafio.android.ui.theme.UserAppTheme

@Composable
fun UserItem(user: User) {
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().background(PrimaryDark),
        verticalAlignment = Alignment.Top,
    ) {
        LoadImage(user.img)

        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp).wrapContentSize(),
                text = user.name,
                color = Color.White,
                style = Typography.labelSmall,
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp).wrapContentSize(),
                text = user.username ?: "",
                color = ColorDetail,
                style = Typography.labelSmall,
            )
        }
    }
}

@Composable
private fun LoadImage(image: String?) {
    Box(modifier = Modifier.size(76.dp), contentAlignment = Alignment.Center) {
        var isLoading by remember { mutableStateOf(true) }
        if (isLoading) {
            CircularProgressIndicator(color = Accent, modifier = Modifier.padding(all = 8.dp))
        }
        AsyncImage(
            modifier =
                Modifier.size(52.dp)
                    .clip(CircleShape)
                    .padding(top = 12.dp, bottom = 12.dp, start = 24.dp),
            model = image,
            onSuccess = { isLoading = false },
            onError = { isLoading = false },
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_round_account_circle),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserAppTheme { UserItem(User(id = 1, img = "", name = "Eduardo", username = "Username")) }
}
