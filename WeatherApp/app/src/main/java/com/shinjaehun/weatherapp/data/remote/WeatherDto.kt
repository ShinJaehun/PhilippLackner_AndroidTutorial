package com.shinjaehun.weatherapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
