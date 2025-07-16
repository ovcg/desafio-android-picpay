package com.picpay.desafio.android.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.picpay.desafio.android.UserMock
import com.picpay.desafio.android.ui.components.BTN_ERROR_TEST_TAG
import com.picpay.desafio.android.ui.components.ERROR_TEST_TAG
import com.picpay.desafio.android.ui.components.USER_ITEM_TEST_TAG
import com.picpay.desafio.android.ui.components.UserList
import com.picpay.desafio.android.ui.theme.UserAppTheme
import org.junit.Rule
import org.junit.Test

class UserListTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun showEmptyList() {
        composeTestRule.setContent { UserAppTheme { LazyColumn { UserList(emptyList()) {} } } }
        composeTestRule.onNodeWithTag(ERROR_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithText("Tentar novamente").assertIsDisplayed()
        composeTestRule.onNodeWithTag(BTN_ERROR_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun showUsersList() {
        composeTestRule.setContent {
            UserAppTheme { LazyColumn { UserList(listOf(UserMock.user)) {} } }
        }
        composeTestRule
            .onAllNodes(SemanticsMatcher(USER_ITEM_TEST_TAG, { true }), useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }
}
