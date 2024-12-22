package com.example.android_technique_collection.ui.common.preview

import androidx.compose.ui.tooling.preview.Preview

annotation class DelayedPreview(val delay: Long)

@Preview(locale = "en-US")
@Preview(locale = "ja")
@Preview(device = "spec:width=720px,height=1280px,dpi=300",  locale = "ja", fontScale = 2f)
annotation class MultiPreviews