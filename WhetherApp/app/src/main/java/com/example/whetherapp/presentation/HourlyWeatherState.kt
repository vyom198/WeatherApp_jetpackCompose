package com.example.whetherapp.presentation

import com.example.whetherapp.domain.model.Hourly

data class HourlyWeatherState(
    val isLoading: Boolean = false,
    val data: List<Hourly>? = null,
    val error: String? = null
)
