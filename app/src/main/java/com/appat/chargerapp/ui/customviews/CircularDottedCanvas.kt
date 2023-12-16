package com.appat.chargerapp.ui.customviews

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularDottedCanvas(modifier: Modifier = Modifier,
                         numberOfCircles: Int = 200) {
    Canvas(
        modifier = modifier
    ) {
        val radius = size.width * .4f
        val lineDegree = (360f * 2) / numberOfCircles

        for (circleNumber in 0..numberOfCircles) {

            val angleInDegrees = lineDegree * circleNumber - 90f
            val angleRad = Math.toRadians(angleInDegrees.toDouble()).toFloat()

            val dotRadius = 2.dp

            val dotColor = Color.White

            val dotDistanceFromMainCircle = radius * .15f
            drawCircle(
                center = Offset(
                    x = (radius + dotDistanceFromMainCircle) * cos(angleRad) + size.center.x,
                    y = (radius + dotDistanceFromMainCircle) * sin(angleRad) + size.center.y
                ),
                color = dotColor,
                radius = dotRadius.toPx()
            )
        }
    }
}