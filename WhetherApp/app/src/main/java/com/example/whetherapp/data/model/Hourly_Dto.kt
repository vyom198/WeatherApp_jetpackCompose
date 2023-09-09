package com.example.whetherapp.data.model

data class Hourly_Dto(
    val precipitation: List<Double>,
    val rain: List<Double>,
    val snowfall: List<Double>,
    val surface_pressure: List<Double>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val uv_index: List<Double>,
    val visibility: List<Double>,
    val weathercode: List<Int>,
    val windspeed_120m: List<Double>
)