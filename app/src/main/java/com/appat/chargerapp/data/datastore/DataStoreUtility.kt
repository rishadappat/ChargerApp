package com.appat.chargerapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

enum class ThemeData {
    LIGHT, DARK, AUTO
}

@InstallIn(SingletonComponent::class)
@Module
class DataStoreUtility(@ApplicationContext private val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    private val THEME = stringPreferencesKey("theme")

    val theme: Flow<String> = context.dataStore.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[THEME] ?: ThemeData.LIGHT.name
    }

    suspend fun saveTheme(theme: ThemeData) {
        context.dataStore.edit { settings ->
            settings[THEME] = theme.name
        }
    }
}