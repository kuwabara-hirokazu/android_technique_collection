package com.example.android_technique_collection.feature.chart.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    PieCharts(
        modifier = Modifier.size(100.dp),
        elements = PieChartsElement.fake()
    )
}
