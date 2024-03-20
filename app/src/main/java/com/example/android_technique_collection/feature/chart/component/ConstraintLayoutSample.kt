package com.example.android_technique_collection.feature.chart.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstraintLayoutSample() {
    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
        // コンポーザブルの参照を作成
        val (box1, box2, box3, box4, line) = createRefs()

        // ボックス1
        Box(
            Modifier
                .size(100.dp)
                .background(Color.Red)
                .constrainAs(box1) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        // ボックス2
        Box(
            Modifier
                .size(150.dp)
                .background(Color.Green)
                .constrainAs(box2) {
                    top.linkTo(box1.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        // ボックス3
        Box(
            Modifier
                .size(200.dp)
                .background(Color.Blue)
                .constrainAs(box3) {
                    top.linkTo(box2.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        // ボックス1, 2, 3の下端に基づいてトップバリアを作成
        val topBarrier = createTopBarrier(box1, box2, box3)

        // ボックス1, 2, 3の終端に基づいてスタートバリアを作成
        val startBarrier = createEndBarrier(box1, box2, box3)

        // ボックス4
        Box(
            Modifier
                .width(60.dp)
                .background(Color.Yellow)
                .constrainAs(box4) {
                    top.linkTo(topBarrier)
                    bottom.linkTo(box3.top)
                    start.linkTo(startBarrier)
                    height = Dimension.fillToConstraints // ボックス4の高さを動的に設定
                }
        )

        Canvas(modifier = Modifier
            .width(300.dp)
            .constrainAs(line) {
                bottom.linkTo(box3.top)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end)
            }
        ) {
            drawLine(
                color = Color.Black,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 6f
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConstraintLayoutSamplePreview() {
    ConstraintLayoutSample()
}
