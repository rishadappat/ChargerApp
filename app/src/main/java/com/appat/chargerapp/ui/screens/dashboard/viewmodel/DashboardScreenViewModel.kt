package com.appat.chargerapp.ui.screens.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appat.chargerapp.data.models.ChargingStation
import com.appat.chargerapp.data.repository.ChargingStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    private val chargingStationRepository: ChargingStationRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel()  {

    private val _uiState = MutableStateFlow(DashboardScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getChargingStations()
    }

    private fun getChargingStations() = viewModelScope.launch(dispatcher) {
        val chargingStations = chargingStationRepository.getChargingStations()
        _uiState.update { currentState ->
            currentState.copy(
                allStations = chargingStations.stations
            )
        }
        _uiState.update { currentState ->
            currentState.copy(
                filteredStations = chargingStations.stations.filter { it.name.contains(_uiState.value.searchText, true) }
            )
        }
    }

    fun performSearch(searchText: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchText = searchText
            )
        }
        _uiState.update { currentState ->
            currentState.copy(
                filteredStations = _uiState.value.allStations.filter { it.name.contains(_uiState.value.searchText, true) }
            )
        }
    }
}

data class DashboardScreenState(
    val searchText: String = "",
    val allStations: List<ChargingStation> = listOf(),
    val filteredStations: List<ChargingStation> = listOf()
)