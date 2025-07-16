package com.picpay.desafio.android.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.picpay.desafio.android.ui.theme.UserAppTheme
import com.picpay.desafio.android.utils.mock.UserMock
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
        composeTestRule.setContent { UserAppTheme { LazyColumn { UserList(UserMock.users()) {} } } }
        composeTestRule
            .onAllNodes(SemanticsMatcher(USER_ITEM_TEST_TAG, { true }), useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }
}
