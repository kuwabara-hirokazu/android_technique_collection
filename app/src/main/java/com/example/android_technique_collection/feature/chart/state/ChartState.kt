package com.example.android_technique_collection.feature.chart.state

import androidx.compose.ui.graphics.Color

data class ChartState(
    val value: String
)

data class PieChartsElement(
    val pieDegrees: Float,
    val color: Color
)
