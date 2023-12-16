package com.appat.chargerapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.appat.chargerapp.data.models.ChargingStation
import com.appat.chargerapp.ui.customviews.ChargingStationListItem
import com.appat.chargerapp.ui.customviews.CommonAppBar
import com.appat.chargerapp.ui.customviews.SearchField
import com.appat.chargerapp.ui.screens.dashboard.viewmodel.DashboardScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(onClick: (ChargingStation) -> Unit = {}) {
    val viewModel: DashboardScreenViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CommonAppBar(title = "Search")
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            Box(modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .padding(20.dp)) {
                SearchField(placeholder = "Search", onValueChange = {
                    viewModel.performSearch(it)
                })
            }
            LazyColumn(
                Modifier.fillMaxSize(),
            ) {
                itemsIndexed(uiState.value.filteredStations, key = { _, item ->
                    item.id
                }, itemContent = { _, item ->
                    ChargingStationListItem(chargingStation = item,
                        modifier = Modifier.animateItemPlacement(), onClick = onClick)
                })
            }
        }
    }
}