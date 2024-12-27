package com.example.android_technique_collection.ui.common.preview

import androidx.compose.ui.tooling.preview.Preview

annotation class DelayedPreview(val delay: Long)

@Preview(name = "Default Language")
@Preview(name = "Japanese", locale = "ja")
@Preview(
    name = "Large Display Size",
    locale = "ja",
    fontScale = 2f,
    device = "spec:width=1080px,height=1920px,dpi=420"
)
@Preview(
    name = "Small Display Size",
    locale = "ja",
    fontScale = 2f,
    device = "spec:width=360px,height=640px,dpi=160"
)
annotation class MultiPreviews