package com.appat.chargerapp.ui.screens.stationdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appat.chargerapp.data.models.ChargingStation
import com.appat.chargerapp.data.repository.ChargingStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StationDetailsViewModel @Inject constructor(
    private val chargingStationRepository: ChargingStationRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(StationDetailState())
    val uiState = _uiState.asStateFlow()

    fun getChargingStationById(id: Long) = viewModelScope.launch(Dispatchers.Default) {
        val allStations = chargingStationRepository.getChargingStations().stations
        val station = allStations.filter { it.id == id }
        _uiState.update { currentState ->
            currentState.copy(
                station = station.firstOrNull()
            )
        }
    }

}

data class StationDetailState(
    val station: ChargingStation? = null
)