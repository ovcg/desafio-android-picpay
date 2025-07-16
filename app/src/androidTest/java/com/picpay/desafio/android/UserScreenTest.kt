package com.picpay.desafio.android

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.picpay.desafio.android.ui.UserScreen
import com.picpay.desafio.android.ui.model.UserState
import com.picpay.desafio.android.ui.theme.UserAppTheme
import org.junit.Rule
import org.junit.Test

class UserScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun showUserScreen() {
        composeTestRule.setContent { UserAppTheme { UserScreen(UserState.Success(listOf())) } }
        composeTestRule.onNodeWithText("Contatos").assertIsDisplayed()
    }
}
