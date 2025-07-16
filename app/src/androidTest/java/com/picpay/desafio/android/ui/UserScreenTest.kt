package com.picpay.desafio.android.ui

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import com.picpay.desafio.android.ui.components.USER_ITEM_TEST_TAG
import com.picpay.desafio.android.ui.model.UserState
import com.picpay.desafio.android.ui.theme.UserAppTheme
import com.picpay.desafio.android.utils.mock.UserMock
import org.junit.Rule
import org.junit.Test

class UserScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showUserScreen() {
        composeTestRule.setContent { UserAppTheme { UserScreen(UserState.Success(listOf())) } }
        composeTestRule.onNodeWithText("Contatos").assertIsDisplayed()
    }

    @Test
    fun showUserScreenWithListOfUsers() {
        composeTestRule.setContent { UserAppTheme { UserScreen(UserState.Success(UserMock.users())) } }
        composeTestRule.onNodeWithText("Contatos").assertIsDisplayed()
        composeTestRule
            .onAllNodes(SemanticsMatcher(USER_ITEM_TEST_TAG, { true }), useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }
}