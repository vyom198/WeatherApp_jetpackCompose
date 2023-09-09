package com.example.whetherapp.domain.model

import java.time.LocalDateTime

data class Hourly(
    val temperature_2m: Double,
    val time: LocalDateTime,
    val windspeed_120m: Double,
    val weatherType: WeatherType
)