package com.example.whetherapp.data.network

import com.example.whetherapp.data.model.AllWeather_Dto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Weatherapi {
@GET("/v1/forecast?&windspeed_unit=ms&hourly=temperature_2m,precipitation,rain,snowfall,weathercode,surface_pressure,visibility,windspeed_120m,uv_index&daily=weathercode," +
        "temperature_2m_max,temperature_2m_min&current_weather=true&timezone=auto")
suspend fun  getAllWhetherData(

    @Query("latitude") lat: Double,
    @Query("longitude") long:Double

):Response<AllWeather_Dto>



}