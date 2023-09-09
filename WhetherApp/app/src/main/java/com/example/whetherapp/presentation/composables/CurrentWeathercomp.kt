package com.example.whetherapp.presentation.composables

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whetherapp.domain.model.CurrentWeather
import com.example.whetherapp.presentation.CurrentWeatherState
import com.example.whetherapp.presentation.CurrentWeatherViewModel
import com.example.whetherapp.presentation.DailyweatherState

@Composable
fun CurrentWeathercard(
    modifier: Modifier,
    state:CurrentWeatherState,
  state1:DailyweatherState,
    currentWeatherViewModel: CurrentWeatherViewModel

) {
    state.data.let {
         Log.d("weatherCard",it?.temperature.toString())
        Card(modifier = modifier
            .padding(14.dp)
            .fillMaxWidth()
            .height(450.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.outlinedCardElevation(10.dp)


        ) {
              Column(
                horizontalAlignment = Alignment.Start


              ) {

                  Text(text =  currentWeatherViewModel.city.value, fontSize = 45.sp, fontStyle = FontStyle.Normal,
                      fontWeight = FontWeight.ExtraBold ,
                      modifier = Modifier.padding(start = 13.dp) )
                  Text(text =  "${it?.temperature}°", fontSize = 45.sp, fontStyle = FontStyle.Normal,
                     fontWeight = FontWeight.ExtraBold, modifier =
                      Modifier.padding(start = 13.dp, bottom = 13.dp))

                 Spacer(modifier = Modifier.height(3.dp))

                  Text(text =  "${it?.weatherType?.weatherDesc}",
                      fontSize = 13.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Light,
                      modifier = Modifier.padding(start = 13.dp))

                  Spacer(modifier = Modifier.height(0.5.dp))
                  Text(text = "${it?.windSpeed} m/s", fontSize =
                  13.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Light,
                      modifier = Modifier.padding(start = 13.dp))
                  Divider(
                      modifier = Modifier
                          .padding(20.dp)
                          .fillMaxWidth()
                          .height(2.dp),
                      color = Color.LightGray, thickness = 0.3.dp
                  )

                DailyWeather( state = state1)
              }



        }



    }

}