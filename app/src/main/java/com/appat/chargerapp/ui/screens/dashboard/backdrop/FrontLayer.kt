package com.appat.chargerapp.ui.screens.dashboard.backdrop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.appat.chargerapp.data.models.ChargingStation
import com.appat.chargerapp.ui.customviews.ChargingInfoView
import com.appat.chargerapp.ui.customviews.ChargingStationListItem
import com.appat.chargerapp.ui.customviews.RevealButton
import com.appat.chargerapp.ui.screens.dashboard.viewmodel.DashboardScreenViewModel
import com.appat.chargerapp.ui.shape.NotchedRoundedCorners
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FrontLayer(scaffoldState: BackdropScaffoldState,
               onItemClick: (ChargingStation) -> Unit = {},
               onViewAllClick: () -> Unit = {}) {
    val viewModel: DashboardScreenViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val revealed = scaffoldState.targetValue == BackdropValue.Revealed
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxWidth()) {
        Column(
            Modifier
                .padding(top = 10.dp)
                .background(
                    shape = NotchedRoundedCorners(35.dp, 30.dp),
                    color = MaterialTheme.colors.background
                )) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Statistics",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Filled.MoreHoriz,
                        modifier = Modifier.size(40.dp),
                        contentDescription = "Options")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 10.dp)
            ) {
                item {
                    ChargingInfoView(modifier = Modifier.fillMaxWidth())
                }
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Nearby Superchargers",
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp)
                        Spacer(Modifier.weight(1f))
                        TextButton(onClick = { onViewAllClick() }) {
                            Text(text = "View All", color = MaterialTheme.colors.onSecondary)
                        }
                    }
                }
                item {
                    Column {
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                    }
                }
                items(uiState.value.allStations.size) {
                    val item = uiState.value.allStations[it]
                    ChargingStationListItem(chargingStation = item, onClick = onItemClick)
                }
            }
        }
        AnimatedVisibility(modifier = Modifier
            .align(Alignment.TopCenter)
            .offset(y = -(20).dp),
            visible = !revealed, enter = scaleIn(
            animationSpec = tween(300, easing = EaseIn)
        ), exit = scaleOut(
            animationSpec = tween(300, easing = EaseOut)
        )
        ) {
            RevealButton(onClick = {
                scope.launch {
                    scaffoldState.reveal()
                }
            })
        }
    }
}