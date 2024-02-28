package com.appat.chargerapp.ui.screens.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.appat.chargerapp.data.models.ChargingStation
import com.appat.chargerapp.ui.customviews.CommonAppBar
import com.appat.chargerapp.ui.screens.dashboard.backdrop.BackLayer
import com.appat.chargerapp.ui.screens.dashboard.backdrop.FrontLayer
import com.appat.chargerapp.ui.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(onItemClick: (ChargingStation) -> Unit = {},
                    onViewAllClick: () -> Unit = {}) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val progress = remember {
        derivedStateOf {
            if(scaffoldState.progress.to != BackdropValue.Revealed) {
                scaffoldState.progress.fraction
            } else {
                1f - scaffoldState.progress.fraction
            }
        }
    }
    BackdropScaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        appBar = {
            CommonAppBar(title = "Good morning, John")
        },
        peekHeight = AppTheme.dimens.peekHeight,
        backLayerContent = {
            BackLayer(progress = progress)
        },
        backLayerBackgroundColor = MaterialTheme.colors.primary,
        frontLayerShape = RectangleShape,
        frontLayerBackgroundColor = Color.Transparent,
        frontLayerScrimColor = Color.Unspecified,
        frontLayerElevation = 0.dp,
        frontLayerContent = {
            FrontLayer(scaffoldState = scaffoldState,
                onItemClick = onItemClick,
                onViewAllClick = onViewAllClick)
        }
    )
}