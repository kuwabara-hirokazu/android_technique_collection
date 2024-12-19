package com.example.android_technique_collection

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.android_technique_collection.feature.home.HomeScreen
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(RobolectricTestRunner::class)
class FirstRobolectricComposeTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test() {
        composeRule.setContent {
            HomeScreen({}, {})
        }

        composeRule
            .onNode(hasText("導線一覧"))
            .assertExists()
    }

    @Test
    fun roborazziTest() {
        composeRule.setContent {
            HomeScreen({}, {})
        }
        composeRule
            .onNode(hasText("導線一覧"))
            .captureRoboImage()
        composeRule
            .onRoot()
            .captureRoboImage()
    }
}