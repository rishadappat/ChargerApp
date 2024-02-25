package com.appat.chargerapp.data.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataStoreViewModel@Inject constructor(
    private val dataStoreUtility: DataStoreUtility,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    val theme = dataStoreUtility.theme
    fun changeTheme(themeData: ThemeData) = viewModelScope.launch(dispatcher) {
        dataStoreUtility.saveTheme(themeData)
    }
}