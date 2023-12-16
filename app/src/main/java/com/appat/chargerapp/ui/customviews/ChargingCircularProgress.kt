package com.appat.chargerapp.ui.customviews

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appat.chargerapp.ui.theme.AppTheme

@Composable
fun ChargingCircularProgress(
    modifier: Modifier,
    progress: Float,
    indicatorSize: Dp = AppTheme.dimens.indicatorSize,
    animationDuration: Int = 2000) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")

    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        ), label = "rotateAnimation"
    )

    Box(modifier = modifier
        .size(size = indicatorSize)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.ElectricBolt,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = "Charging")
            Text(text = "${(progress * 100).toInt()}%",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 10.sp)
        }
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .rotate(degrees = rotateAnimation),
            backgroundColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.3f),
            strokeCap = StrokeCap.Round,
            progress = progress,
            strokeWidth = 8.dp,
            color = MaterialTheme.colors.onPrimary
        )
    }
}