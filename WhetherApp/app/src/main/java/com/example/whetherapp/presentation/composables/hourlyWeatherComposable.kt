package com.example.whetherapp.presentation.composables

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whetherapp.domain.model.Hourly
import com.example.whetherapp.presentation.HourlyWeatherState
import java.time.format.DateTimeFormatter

@SuppressLint("RememberReturnType")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Hourlyweathercomp(
    state : HourlyWeatherState,
    modifier: Modifier
) {
    state.data?.let {

        
        val formatter = DateTimeFormatter.ofPattern("EEEE, d MMM", java.util.Locale.ENGLISH)
        val today = remember(it) {
            it[0].time.format(formatter).uppercase()
        }
        
        Card(modifier = modifier
            .padding(14.dp)
            .fillMaxWidth()
            .height(400.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.outlinedCardElevation(10.dp)
        ) {
            
            Text(text = today ,fontSize = 20.sp, fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(14.dp),)
            LazyColumn{

                items(it){hourlyweather->

                HourlyweatherItem(hourlyweather)


                }


            }
        }





    }





}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyweatherItem(hourlyweather: Hourly) {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("ha")


Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically,

    ) {

    Text(text = hourlyweather.time.format(dateTimeFormatter),
        fontSize = 15.sp,fontStyle= FontStyle.Normal,
        fontWeight = FontWeight.Light, modifier = Modifier.padding(15.dp))


    Spacer(modifier = Modifier.width(50.dp))

    Image(painter = painterResource(id =hourlyweather.weatherType.iconRes),
        contentDescription =null, modifier = Modifier.size(50.dp) )

    Spacer(modifier = Modifier.width(50.dp))
    Text(text =" ${hourlyweather.temperature_2m}°")

    Spacer(modifier = Modifier.width(40.dp))
    Text(text = " ${hourlyweather.windspeed_120m} m/s")




}



}
