package com.appat.chargerapp.ui.customviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun RevealButton(modifier: Modifier = Modifier, onClick: ()->Unit) {
    IconButton(modifier = modifier
        .clip(CircleShape)
        .size(75.dp),
        onClick = {
            onClick()
        }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier.size(30.dp)
                    .align(Alignment.Center)
                    .offset(y = 10.dp),
                imageVector = Icons.Default.KeyboardArrowDown,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = "Open Drawer")
        }
    }
}