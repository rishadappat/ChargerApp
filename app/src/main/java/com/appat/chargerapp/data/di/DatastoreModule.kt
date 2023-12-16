package com.appat.chargerapp.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Singleton
    @Provides
    fun providesDatastore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore;
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("chargerApp")