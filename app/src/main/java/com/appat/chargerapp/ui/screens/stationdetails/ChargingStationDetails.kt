package com.appat.chargerapp.ui.screens.stationdetails

import android.location.Location
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.EvStation
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.appat.chargerapp.R
import com.appat.chargerapp.ui.screens.mapscreen.StationMarker
import com.appat.chargerapp.utility.getDistance
import com.appat.chargerapp.utility.getDummyUserLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChargingStationDetails(id: Long?) {
    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: StationDetailsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val station = uiState.value.station
    val userLocation by remember {
        mutableStateOf(getDummyUserLocation())
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getChargingStationById(id ?: 0)
    })
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(
            station?.gps?.latitude ?: userLocation.latitude,
            station?.gps?.longitude ?: userLocation.longitude), 10f)
    }
    LaunchedEffect(key1 = station, block = {
        if(station != null) {
            Log.d("Station", station.toString())
            val cameraUpdate = CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(
                LatLng(station.gps.latitude, station.gps.longitude), 10f))
            scope.launch {
                cameraPositionState.animate(update = cameraUpdate)
            }
        }
    })
    BottomSheetScaffold(
        scaffoldState = state,
        sheetPeekHeight = 100.dp,
        sheetElevation = 40.dp,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
        ChargingStationContent(uiState)
    }) {
        Box(modifier = Modifier
            .fillMaxSize()) {
            val mapStyle by remember {
                mutableStateOf(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style))
            }
            val mapUiSettings by remember {
                mutableStateOf(
                    MapUiSettings(mapToolbarEnabled = false,
                        myLocationButtonEnabled = false,
                        zoomControlsEnabled = false)
                )
            }
            val properties by remember {
                mutableStateOf(
                    MapProperties(
                    isMyLocationEnabled = false,
                    isBuildingEnabled = true,
                    mapStyleOptions = mapStyle)
                )
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = mapUiSettings,
                properties = properties,
                contentPadding = PaddingValues(bottom = 110.dp)
            ) {
                if(station != null) {
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
fun ChargingStationContent(uiState: State<StationDetailState>) {
    val chargingStation = uiState.value.station
    if(chargingStation != null) {
        val location = Location("")
        location.latitude = chargingStation.gps.latitude
        location.longitude = chargingStation.gps.longitude
        val distance = getDistance(location, getDummyUserLocation())
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp))
        {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center) {
                Text(chargingStation.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = chargingStation.address.getAddress(),
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSecondary
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(Modifier,
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                    imageVector = Icons.Default.EvStation,
                    contentDescription = "Number of chargers")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "${chargingStation.stallCount} Chargers",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(Modifier,
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                    imageVector = Icons.Default.BatteryChargingFull,
                    contentDescription = "Power")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "${chargingStation.powerKilowatt} Kw",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(Modifier,
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                    imageVector = Icons.Default.Route,
                    contentDescription = "Distance")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "%.2f Km".format(distance),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {  }) {
                Icon(imageVector = Icons.Default.NearMe, contentDescription = "Directions")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Directions")
            }
        }
    }
}