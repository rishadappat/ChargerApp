package com.appat.chargerapp.ui.screens.mapscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.appat.chargerapp.R
import com.appat.chargerapp.ui.customviews.CommonAppBar
import com.appat.chargerapp.ui.screens.dashboard.viewmodel.DashboardScreenViewModel
import com.appat.chargerapp.utility.bitmapDescriptor
import com.appat.chargerapp.utility.getDummyUserLocation
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val viewModel: DashboardScreenViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val darkMode = isSystemInDarkTheme()
    val isDark by remember {
        mutableStateOf(darkMode)
    }
    val mapStyleFile = if(isDark) R.raw.map_style_dark else R.raw.map_style
    val mapStyle by remember {
        mutableStateOf(MapStyleOptions.loadRawResourceStyle(context, mapStyleFile))
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                zoomControlsEnabled = false)
        )
    }
    val properties by remember {
        mutableStateOf(MapProperties(
            isMyLocationEnabled = false,
            isBuildingEnabled = true,
            mapStyleOptions = mapStyle))
    }
    val userLocation by remember {
        mutableStateOf(getDummyUserLocation())
    }
    val positionState = remember {
        CameraPosition.fromLatLngZoom(LatLng(userLocation.latitude, userLocation.longitude), 10f)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = positionState
    }

    Scaffold(
        topBar = {
            CommonAppBar(title = "Charging Stations")
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = mapUiSettings,
                properties = properties,
            ) {
                uiState.value.allStations.forEach { station ->
                    StationMarker(
                        state = LatLng(station.gps.latitude, station.gps.longitude),
                        title = station.name,
                        snippet = station.address.state,
                        iconResourceId = R.drawable.asset_1
                    )
                }
            }
        }
    }
}

@Composable
fun StationMarker(
    state: LatLng,
    title: String,
    snippet: String,
    @DrawableRes iconResourceId: Int
) {
    val context = LocalContext.current
    val icon = bitmapDescriptor(
        context, iconResourceId
    )

    val markerState = MarkerState(position = state)

    Marker(
        state = markerState,
        title = title,
        snippet = snippet,
        icon = icon
    )
}