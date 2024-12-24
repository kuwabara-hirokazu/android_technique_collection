package com.example.android_technique_collection.feature.chart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun BarChart() {
    val dataPoints = listOf(50, -100, -10, 30, 60, 100) // グラフに表示するデータポイント

    ConstraintLayout(
        modifier = Modifier.padding(16.dp)
    ) {
        // 基準線（グラフの中心線）のための参照を作成
        val baselineRef = createRef()
        // 各棒のための参照を作成
        val refs = dataPoints.map { createRef() }

        // 基準線の描画
        Box(
            modifier = Modifier
                .constrainAs(baselineRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    // 基準線をConstraintLayoutの真ん中に配置
                    centerVerticallyTo(parent)
                }
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Black)
        )

        // データポイントに基づいて棒を描画
        dataPoints.forEachIndexed { index, value ->
            Box(
                modifier = Modifier
                    .constrainAs(refs[index]) {
                        if (index == 0) {
                            start.linkTo(parent.start)
                        } else {
                            start.linkTo(refs[index - 1].end, margin = 16.dp)
                        }
                        // 正の値の場合は基準線から上に、負の値の場合は基準線から下に配置
                        if (value > 0) {
                            bottom.linkTo(baselineRef.top)
                        } else {
                            top.linkTo(baselineRef.bottom)
                        }
                        height = if (value > 0) Dimension.value(value.dp)
                        else Dimension.value((-value).dp)
                    }
                    .width(40.dp)
                    .background(if (value > 0) Color.Blue else Color.Red)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChartPreview() {
    BarChart()
}
