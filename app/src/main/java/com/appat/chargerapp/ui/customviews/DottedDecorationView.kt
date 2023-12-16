package com.appat.chargerapp.ui.customviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DottedDecorationView(modifier: Modifier) {
    Box(modifier = modifier
        .aspectRatio(1f)) {
        CircularDottedCanvas(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .align(Alignment.BottomCenter),
            numberOfCircles = 200)
        CircularDottedCanvas(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .aspectRatio(1f)
                .align(Alignment.BottomCenter),
            numberOfCircles = 180)
        CircularDottedCanvas(
            modifier = Modifier
                .fillMaxSize(0.8f)
                .aspectRatio(1f)
                .align(Alignment.BottomCenter),
            numberOfCircles = 160)
        CircularDottedCanvas(
            modifier = Modifier
                .fillMaxSize(0.7f)
                .aspectRatio(1f)
                .align(Alignment.BottomCenter),
            numberOfCircles = 140)
    }
}