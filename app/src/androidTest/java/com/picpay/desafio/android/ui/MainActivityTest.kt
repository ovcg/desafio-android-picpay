package com.picpay.desafio.android.ui

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import com.picpay.desafio.android.ui.components.USER_ITEM_TEST_TAG
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun displayMainScreen() {
        composeTestRule.onNodeWithText("Contatos").assertIsDisplayed()
        composeTestRule
            .onAllNodes(SemanticsMatcher(USER_ITEM_TEST_TAG, { true }), useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }
}
