package com.example.whetherapp.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.whetherapp.data.model.CurrentWeather_Dto
import com.example.whetherapp.data.model.Daily_Dto
import com.example.whetherapp.data.model.Hourly_Dto
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Mapper {


    fun CurrentWeather_Dto.toDomainModel(): CurrentWeather {
        val weatherType = WeatherType.fromWMO(weathercode)
        return CurrentWeather(is_day, temperature, time, weatherType, winddirection, windspeed)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Daily_Dto.toDailyWeatherList(): List<Daily> {

        val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

        return time.mapIndexed { index, time ->
            val minTemperature = temperature_2m_min[index]
            val maxTemperature = temperature_2m_max[index]
            val weatherCode = weathercode[index]
            val localDateTime = LocalDate.parse(time, dateTimeFormatter)

              val daily = Daily(
                  temperature_2m_min = minTemperature,
                  temperature_2m_max = maxTemperature,
                  time = localDateTime,
                  weatherType = WeatherType.fromWMO(weatherCode),

              )
            daily
        }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun Hourly_Dto.toHourlyWeatherList(): List<Hourly> {
        val dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME

        val currentTime = LocalDateTime.now()
        return time.mapIndexedNotNull { index, time ->
            val temperature = temperature_2m[index]
            val weatherCode = weathercode[index]
            val windspeed = windspeed_120m[index]
            val localDateTime = LocalDateTime.parse(time, dateTimeFormatter)

          val hourly  =
              if (localDateTime.toLocalDate() == currentTime.toLocalDate()) {
                  Hourly(
                      temperature_2m = temperature,
                      time = localDateTime,
                      windspeed_120m = windspeed,
                      weatherType = WeatherType.fromWMO(weatherCode)
                  )
              }else null

            hourly
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
     fun mapDailyWeatherByDayOfWeek(dailyWeatherList: List<Daily>): Map<DayOfWeek, List<Daily>> {
        val dailyWeatherMap = mutableMapOf<DayOfWeek, MutableList<Daily>>()

        dailyWeatherList.forEach { dailyWeather ->
            val dayOfWeek = dailyWeather.time.dayOfWeek
            dailyWeatherMap.getOrPut(dayOfWeek) { mutableListOf() }.add(dailyWeather)
        }

        return dailyWeatherMap
    }
}