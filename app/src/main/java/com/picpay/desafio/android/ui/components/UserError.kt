package com.picpay.desafio.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.theme.PrimaryDark

const val ERROR_TEST_TAG = "error_test"
const val BTN_ERROR_TEST_TAG = "btn_error_test"

@Preview
@Composable
fun UserError(
    message: Int = R.string.error,
    icon: Int = R.drawable.outline_error_24,
    onReload: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize().background(PrimaryDark).testTag(ERROR_TEST_TAG),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.wrapContentSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(icon),
                contentDescription = null,
            )
            DefaultSpacer()
            Text(text = stringResource(message), color = White)
            DefaultSpacer()
            Button(modifier = Modifier.testTag(BTN_ERROR_TEST_TAG), onClick = { onReload() }) {
                Text(text = stringResource(R.string.btn_error))
            }
        }
    }
}

@Composable
private fun DefaultSpacer() {
    Spacer(modifier = Modifier.height(16.dp))
}
