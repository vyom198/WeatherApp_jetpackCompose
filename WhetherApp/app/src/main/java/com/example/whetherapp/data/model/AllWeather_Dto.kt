package com.example.whetherapp.data.model

data class AllWeather_Dto(
    val current_weather: CurrentWeather_Dto,
    val daily: Daily_Dto,
    val daily_units: DailyUnits_Dto,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly_Dto,
    val hourly_units: HourlyUnits_Dto,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)