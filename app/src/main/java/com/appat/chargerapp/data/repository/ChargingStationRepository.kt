package com.appat.chargerapp.data.repository

import android.content.Context
import com.appat.chargerapp.R
import com.appat.chargerapp.data.models.ChargerLocations
import com.appat.chargerapp.utility.readRawJson
import javax.inject.Inject

class ChargingStationRepository @Inject constructor(private val context: Context) {
    suspend fun getChargingStations(): ChargerLocations {
        return context.readRawJson(R.raw.charger_locations)
    }
}