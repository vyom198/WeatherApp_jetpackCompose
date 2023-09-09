package com.example.whetherapp.domain.model

import java.time.LocalDate

data class Daily(
    val temperature_2m_max: Double,
    val temperature_2m_min: Double,
    val time: LocalDate,
    val weatherType: WeatherType
)