package com.appat.chargerapp.ui.customviews

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonAppBar(title: String, actions: @Composable RowScope.() -> Unit = {},) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        elevation = 0.dp,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 16.sp)
        },
        actions = actions)
}