package com.example.android_technique_collection

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onRoot
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.example.android_technique_collection.feature.home.HomeScreen
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(RobolectricTestRunner::class)
//@Config(qualifiers = RobolectricDeviceQualifiers.Pixel5) // Optional デバイスの指定
@Config(qualifiers = "+ja") // Optional 言語の指定
class FirstRobolectricComposeTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun captureRoboImageSample() {
        // Tips: You can use Robolectric with Espresso API
        // launch
        ActivityScenario.launch(MainActivity::class.java)

        // Capture screen
        onView(ViewMatchers.isRoot())
            // If you don't specify a screenshot file name, Roborazzi will automatically use the method name as the file name for you.
            // The format of the file name will be as follows:
            // build/outputs/roborazzi/com_..._FirstRobolectricComposeTest_captureRoboImageSample.png
            .captureRoboImage()

        // Capture Jetpack Compose Node
        composeRule.onNodeWithTag("AddBoxButton")
            .onParent()
            .captureRoboImage("build/compose.png")
    }

//    @Test
//    fun roborazziTest() {
//        composeRule.setContent {
//            HomeScreen({}, {})
//        }
//        composeRule
//            .onNode(hasText("導線一覧"))
//            .captureRoboImage()
//        composeRule
//            .onRoot()
//            .captureRoboImage()
//    }
}