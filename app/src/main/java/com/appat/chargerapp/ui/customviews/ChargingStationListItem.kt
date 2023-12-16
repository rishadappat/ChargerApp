package com.appat.chargerapp.ui.customviews

import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appat.chargerapp.data.models.ChargingStation
import com.appat.chargerapp.utility.getDistance
import com.appat.chargerapp.utility.getDummyUserLocation

@Composable
fun ChargingStationListItem(chargingStation: ChargingStation,
                            modifier: Modifier = Modifier,
                            onClick: (ChargingStation) -> Unit = {}) {
    val location = Location("")
    location.latitude = chargingStation.gps.latitude
    location.longitude = chargingStation.gps.longitude
    val distance = getDistance(location, getDummyUserLocation())
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = {
            onClick(chargingStation)
        })
        .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center) {
            Text(chargingStation.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = chargingStation.address.state,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.LocationOn,
                modifier = Modifier.size(30.dp),
                contentDescription = "Location",
                tint = MaterialTheme.colors.onSecondary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "%.2f Km".format(distance),
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSecondary)
        }
    }
}