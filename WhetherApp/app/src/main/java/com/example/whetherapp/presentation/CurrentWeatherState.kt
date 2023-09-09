package com.example.whetherapp.presentation

import com.example.whetherapp.domain.model.CurrentWeather

data class CurrentWeatherState(
    val isLoading : Boolean = false,
    val data : CurrentWeather? = null,
    val error: String? = null

)
