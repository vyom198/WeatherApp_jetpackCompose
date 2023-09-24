package com.example.whetherapp.presentation.states

import com.example.whetherapp.domain.model.Hourly

data class HourlyWeatherState(
    val isLoading: Boolean = false,
    val data: List<Hourly>? = null,
    val error: String? = null
)
