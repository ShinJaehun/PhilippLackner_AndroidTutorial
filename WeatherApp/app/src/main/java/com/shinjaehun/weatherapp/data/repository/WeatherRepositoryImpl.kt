package com.shinjaehun.weatherapp.data.repository

import android.util.Log
import com.shinjaehun.weatherapp.data.mappers.toWeatherInfo
import com.shinjaehun.weatherapp.data.remote.WeatherApi
import com.shinjaehun.weatherapp.domain.repository.WeatherRepository
import com.shinjaehun.weatherapp.domain.util.Resource
import com.shinjaehun.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

private const val TAG = "WeatherRepositoryImpl"
class WeatherRepositoryImpl @Inject constructor(
    // 이건 DI가 아닌가요???
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {

            Log.i(TAG, "${api.getWeatherData(lat = lat, long = long)}")

            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long,
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}