package com.shinjaehun.weatherapp.di

import com.shinjaehun.weatherapp.data.location.DefaultLocationTracker
import com.shinjaehun.weatherapp.data.repository.WeatherRepositoryImpl
import com.shinjaehun.weatherapp.domain.location.LocationTracker
import com.shinjaehun.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}