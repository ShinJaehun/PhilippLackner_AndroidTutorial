package com.shinjaehun.weatherapp.domain.repository

import com.shinjaehun.weatherapp.domain.util.Resource
import com.shinjaehun.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}