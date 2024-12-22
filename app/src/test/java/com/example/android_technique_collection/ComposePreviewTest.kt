package com.example.android_technique_collection

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.DEFAULT_ROBORAZZI_OUTPUT_DIR_PATH
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

//@RunWith(ParameterizedRobolectricTestRunner::class)
//class ComposePreviewTest(
//    private val preview: ComposablePreview<AndroidPreviewInfo>
//) {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @GraphicsMode(GraphicsMode.Mode.NATIVE)
//    @Test
//    fun snapshot() {
//        val fileName = AndroidPreviewScreenshotIdBuilder(preview).ignoreClassName().build()
//        val filePath = "$DEFAULT_ROBORAZZI_OUTPUT_DIR_PATH/$fileName.png" // Preview関数名.png
//
//        composeTestRule.apply {
//            mainClock.autoAdvance = false
//            setContent { preview() }
//            mainClock.advanceTimeBy(1000)
//            onRoot().captureRoboImage(filePath = filePath)
//            mainClock.autoAdvance = true
//        }
//    }
//
//    companion object {
//        private val cachedPreviews: List<ComposablePreview<AndroidPreviewInfo>> by lazy {
//            AndroidComposablePreviewScanner()
//                .scanPackageTrees(
//                    include = listOf("com.example.android_technique_collection"), // Preview関数を探すパッケージを設定する
//                    exclude = listOf()
//                )
//                .includePrivatePreviews() // PrivateなPreview関数も含める
//                .getPreviews()
//        }
//
//        @JvmStatic
//        @ParameterizedRobolectricTestRunner.Parameters
//        fun values(): List<ComposablePreview<AndroidPreviewInfo>> = cachedPreviews
//    }
//}