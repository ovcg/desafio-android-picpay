package com.picpay.desafio.android.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.picpay.desafio.android.ui.theme.UserAppTheme
import com.picpay.desafio.android.utils.mock.UserMock
import org.junit.Rule
import org.junit.Test

class UserItemTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun testeUserScreen() {
        val user = UserMock.user
        composeTestRule.setContent { UserAppTheme { UserItem(user) } }
        composeTestRule.onNodeWithTag(USER_ITEM_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(USER_ITEM_NAME_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(USER_ITEM_USERNAME_TEST_TAG).assertIsDisplayed()
    }
}
