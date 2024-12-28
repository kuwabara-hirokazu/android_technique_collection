package com.example.android_technique_collection

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.Density
import com.example.android_technique_collection.ui.common.preview.DelayedPreview
import com.example.android_technique_collection.ui.common.preview.MultiPreviews
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.robolectric.RuntimeEnvironment
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.core.preview.getAnnotation

@OptIn(ExperimentalRoborazziApi::class)
class MyComposePreviewTester : ComposePreviewTester<AndroidPreviewInfo> {

    // recreate()をするためにcreateComposeRuleではなくcreateAndroidComposeRuleを使用
    private val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    override fun options(): ComposePreviewTester.Options {
        // JUnit 4 RuleとしてcomposeTestRuleを使用
        val testLifecycleOptions = ComposePreviewTester.Options.JUnit4TestLifecycleOptions(
            testRuleFactory = { composeTestRule }
        )
        return super.options().copy(testLifecycleOptions = testLifecycleOptions)
    }

    // Composable Preview Scannerを使って集めたプレビュー関数と、
    // そのメタデータが格納されているクラス（ComposablePreview<AndroidPreviewInfo>）のリストを返す
    override fun previews(): List<ComposablePreview<AndroidPreviewInfo>> {
        val options = options()
        return AndroidComposablePreviewScanner()
            .scanPackageTrees(*options.scanOptions.packages.toTypedArray()) // build.gradleで指定したパッケージをスキャン
            .includePrivatePreviews() // Private Previewもテストに含める
            .includeAnnotationInfoForAllOf(
                DelayedPreview::class.java,
                MultiPreviews::class.java
            ) // 指定のアノテーションを持つPreviewもテストに含める
            .getPreviews()
    }

    // 個々のプレビュー関数の情報（ComposablePreview<AndroidPreviewInfo>）を使ってスクリーンショットを撮るテストを行う
    // delayが指定されている場合は、delay時間だけ進めた後にスクリーンショットを撮る
    override fun test(preview: ComposablePreview<AndroidPreviewInfo>) {
        val delay = preview.getAnnotation<DelayedPreview>()?.delay ?: 0L

        // スクリーンショットファイル名が被ってしまうと上書きされてしまうため、各プレビューごとに一意になるようにする
        val previewScannerFileName =
            AndroidPreviewScreenshotIdBuilder(preview).build()
        val fileName =
            if (delay == 0L) previewScannerFileName else "${previewScannerFileName}_delay$delay"
        val filePath = "$fileName.png"

        preview.myApplyToRobolectricConfiguration()
        // Robolectricを使って設定を変更した場合はActivityを再生成する必要がある
        composeTestRule.activityRule.scenario.recreate()

        composeTestRule.apply {
            // delayedプロパティが指定されているときだけ、自動進行を無効にして
            // スクリーンショットを撮るタイミングを手動で制御する
            try {
                if (delay != 0L) {
                    mainClock.autoAdvance = false
                }
                setContent {
                    // LocalCompositionProviderを使って撮影環境を変える
                    ApplyToCompositionLocal(preview) {
                        preview()
                    }
                }
                if (delay != 0L) {
                    mainClock.advanceTimeBy(delay)
                }
                onRoot().captureRoboImage(filePath = filePath)
            } finally {
                mainClock.autoAdvance = true
            }
        }
    }
}

@Composable
fun ApplyToCompositionLocal(
    preview: ComposablePreview<AndroidPreviewInfo>,
    content: @Composable () -> Unit
) {
    val fontScale = preview.previewInfo.fontScale
    val density = LocalDensity.current
    val customDensity =
        Density(density = density.density, fontScale = density.fontScale * fontScale)
    CompositionLocalProvider(LocalDensity provides customDensity) {
        content()
    }

}

// Robolectricの設定を適用する
fun ComposablePreview<AndroidPreviewInfo>.myApplyToRobolectricConfiguration() {
    val preview = this

    // 画面サイズ
    if (preview.previewInfo.widthDp == 360 && preview.previewInfo.heightDp == 640) {
        RuntimeEnvironment.setQualifiers(RobolectricDeviceQualifiers.SmallPhone)
    }

    // ロケールの設定
    if (preview.previewInfo.locale.isNotEmpty()) {
        RuntimeEnvironment.setQualifiers("+${preview.previewInfo.locale}")
    }
}