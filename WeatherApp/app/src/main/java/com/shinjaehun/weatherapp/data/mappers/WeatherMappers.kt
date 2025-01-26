package com.shinjaehun.weatherapp.data.mappers

import android.util.Log
import com.shinjaehun.weatherapp.data.remote.WeatherDataDto
import com.shinjaehun.weatherapp.data.remote.WeatherDto
import com.shinjaehun.weatherapp.domain.weather.WeatherData
import com.shinjaehun.weatherapp.domain.weather.WeatherInfo
import com.shinjaehun.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val TAG = "WeatherMappers"

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
//        it.time.dayOfMonth
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
//    }.also { println(it.values) }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    Log.i(TAG, "weatherDto's weatherData: $weatherData")
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
//        val hour = if (now.minute < 30) now.hour else now.hour + 1

//        val hour = when {
//            now.minute < 30 -> now.hour
//            now.hour == 23 -> 12.00
//            else -> now.hour + 1
//        }

        val hour = if (now.minute >= 30) now.hour + 1 else now.hour
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}