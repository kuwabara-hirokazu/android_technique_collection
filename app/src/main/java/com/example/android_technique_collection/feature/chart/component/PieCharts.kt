package com.example.android_technique_collection.feature.chart.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_technique_collection.feature.chart.state.PieChartsElement

@Composable
fun PieCharts(
    elements: List<PieChartsElement>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        var startAngle = -90f
        elements.forEach { element ->
            drawArc(
                color = element.color,
                startAngle = startAngle,
                sweepAngle = element.pieDegrees,
                useCenter = true,
            )
            startAngle += element.pieDegrees
        }
    }
}

@Preview
@Composable
private fun PieChartsPreview() {
    fun calculatePieDegrees(ratio: Float): Float {
        return 360f * ratio / 100f
    }

    PieCharts(
        modifier = Modifier.size(100.dp),
        elements = listOf(
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
    )
}
