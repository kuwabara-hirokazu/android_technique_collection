package com.example.android_technique_collection.feature.chart.state

import androidx.compose.ui.graphics.Color

sealed interface ChartState {
    data object Loading: ChartState

    data class Shown(
        val pieChartsElements: List<PieChartsElement>
    ): ChartState

    data class Failure(
        val error: String
    ) : ChartState
}
