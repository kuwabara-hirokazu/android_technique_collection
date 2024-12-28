package com.example.android_technique_collection.ui.common.preview

import androidx.compose.ui.tooling.preview.Preview

annotation class DelayedPreview(val delay: Long)

@Preview(name = "Default")
@Preview(name = "Japanese", locale = "ja")
@Preview(
    name = "LargeFont",
    locale = "ja",
    fontScale = 2f,
)
@Preview(
    name = "SmallScreen_LargeFont",
    locale = "ja",
    fontScale = 2f,
    widthDp = 360,
    heightDp = 640
)
annotation class MultiPreviews