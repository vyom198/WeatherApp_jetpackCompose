package com.example.whetherapp.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.whetherapp.data.network.Weatherapi
import com.example.whetherapp.domain.model.CurrentWeather
import com.example.whetherapp.domain.model.Daily
import com.example.whetherapp.domain.model.Hourly
import com.example.whetherapp.domain.model.Mapper.mapDailyWeatherByDayOfWeek
import com.example.whetherapp.domain.model.Mapper.toDailyWeatherList
import com.example.whetherapp.domain.model.Mapper.toDomainModel
import com.example.whetherapp.domain.model.Mapper.toHourlyWeatherList
import com.example.whetherapp.domain.repositroy.WeatherRepo
import com.example.whetherapp.util.SafeApiRequest
import java.time.DayOfWeek
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val weatherapiService: Weatherapi
):WeatherRepo,SafeApiRequest() {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeather {
        val response_currentWeather = safeApiRequest {
            Log.d("Repository", "Fetching current weather data")
            weatherapiService.getAllWhetherData(lat,lon)
        }
       val apiCurrentWeather_Dto = response_currentWeather.current_weather
        Log.d("Repository", "Current weather data received: $apiCurrentWeather_Dto")

        val domainCurrentWeather = apiCurrentWeather_Dto.toDomainModel()

        return domainCurrentWeather
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getHourlyWeather(lat: Double, lon: Double): List<Hourly> {
       val respons_hourly = safeApiRequest {
           weatherapiService.getAllWhetherData(lat, lon)
       }
          val hourly_weather = respons_hourly.hourly
        val hourlyWeatherList = hourly_weather.toHourlyWeatherList()

        return hourlyWeatherList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDailyWeather(lat: Double, lon: Double): Map<DayOfWeek, List<Daily>> {
        val response_daily = safeApiRequest {
            Log.d("Repository1", "Fetching current weather data")
            weatherapiService.getAllWhetherData(lat,lon)
        }
        val daily_weather = response_daily.daily
        val dailyWeatherList = daily_weather.toDailyWeatherList()
        Log.d("Repository1", "daily weather data received: $dailyWeatherList")

        val dailyWeatherMap = mapDailyWeatherByDayOfWeek(dailyWeatherList)

        return dailyWeatherMap

    }
}