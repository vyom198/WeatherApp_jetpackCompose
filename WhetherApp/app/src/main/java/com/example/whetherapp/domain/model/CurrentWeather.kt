package com.example.whetherapp.domain.model

import com.example.whetherapp.data.model.CurrentWeather_Dto

data class CurrentWeather(

    val isDay: Int,
    val temperature: Double,
    val time: String,
    val weatherType: WeatherType,
    val windDirection: Int,
    val windSpeed: Double



)