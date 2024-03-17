package com.example.android_technique_collection.feature.chart

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.android_technique_collection.feature.chart.state.ChartState
import com.example.android_technique_collection.feature.chart.state.PieChartsElement
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ChartViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState: MutableStateFlow<ChartState> =
        MutableStateFlow(ChartState.Loading)
    val uiState = this._uiState.asStateFlow()

    init {
        getChartData()
    }

    private fun getChartData() {
        val pieChartsElements = listOf(
            PieChartsElement(
                pieDegrees = calculatePieDegrees(50f),
                color = Color.Blue
            ),
            PieChartsElement(
                pieDegrees = calculatePieDegrees(25f),
                color = Color.Green
            ),
            PieChartsElement(
                pieDegrees = calculatePieDegrees(15f),
                color = Color.Red
            ),
            PieChartsElement(
                pieDegrees = calculatePieDegrees(10f),
                color = Color.Gray
            ),
        )

        _uiState.value = ChartState.Shown(
            pieChartsElements = pieChartsElements
        )
    }

    private fun calculatePieDegrees(ratio: Float): Float {
        return 360f * ratio / 100f
    }
}
