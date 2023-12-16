package com.appat.chargerapp.data.di

import android.content.Context
import com.appat.chargerapp.data.repository.ChargingStationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChargingStationRepositoryModule {
    @Provides
    fun provideIDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun providesChargingStationRepository(@ApplicationContext context: Context): ChargingStationRepository {
        return ChargingStationRepository(context)
    }
}