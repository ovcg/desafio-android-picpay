package com.picpay.desafio.android.ui.components

import android.app.Application
import android.os.Build
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.UserMock
import com.picpay.desafio.android.ui.theme.UserAppTheme
import kotlin.test.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(
    instrumentedPackages = ["androidx.loader.content"],
    sdk = [Build.VERSION_CODES.VANILLA_ICE_CREAM],
    application = Application::class,
)
class UserListTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun `should show empty list error when list is empty`() {
        composeTestRule.setContent { UserAppTheme { LazyColumn { UserList(emptyList()) {} } } }
        composeTestRule.onNodeWithTag(ERROR_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithText("Tentar novamente").assertIsDisplayed()
        composeTestRule.onNodeWithTag(BTN_ERROR_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun `should show list of users`() {
        composeTestRule.setContent {
            UserAppTheme { LazyColumn { UserList(listOf(UserMock.user)) {} } }
        }
        composeTestRule
            .onAllNodes(SemanticsMatcher(USER_ITEM_TEST_TAG, { true }), useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }
}
