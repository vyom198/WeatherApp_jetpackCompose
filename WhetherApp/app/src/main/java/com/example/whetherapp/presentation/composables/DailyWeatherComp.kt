package com.example.whetherapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whetherapp.domain.model.Daily
import com.example.whetherapp.presentation.DailyweatherState
import java.time.DayOfWeek

@Composable
fun DailyWeather(

    state : DailyweatherState
) {

    WeatherLazyRow(weatherData = state.data)


}

@Composable
fun WeatherLazyRow(weatherData: Map<DayOfWeek, List<Daily>>?) {
    LazyRow {
        weatherData?.entries?.let {
            items(it.toList()) { (dayOfWeek,dailyWeatherList)->
                WeatherItem(dayOfWeek, dailyWeatherList)
            }
        }
    }
}

@Composable
fun WeatherItem(dayOfWeek: DayOfWeek, dailyWeatherList: List<Daily>) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the day of the week
        Text(text = dayOfWeek.toString())
        Spacer(modifier = Modifier.height(6.dp))
        // Display daily weather data
        dailyWeatherList.forEach { dailyWeatherItem ->

           Image(painter = painterResource(id = dailyWeatherItem.weatherType.iconRes) ,
               contentDescription =null, modifier = Modifier.size(50.dp) )

            Text(
                text = "${dailyWeatherItem.temperature_2m_max}°",
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "${dailyWeatherItem.temperature_2m_min}°",
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}