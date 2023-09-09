package com.example.whetherapp.presentation.composables

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box



import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Spacer

import androidx.compose.ui.Alignment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whetherapp.presentation.CurrentWeatherViewModel
import com.example.whetherapp.presentation.DailyweatherViewmodel
import com.example.whetherapp.presentation.HourlyWeatherViewmodel


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@Composable
fun AllWeatherComposable( ) {

  val  viewModel: CurrentWeatherViewModel = hiltViewModel()

  val  dailyweatherViewmodel: DailyweatherViewmodel = hiltViewModel()
  val   hourlyWeatherViewmodel : HourlyWeatherViewmodel = hiltViewModel()


    lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

 val state = viewModel.state
    val state1 = dailyweatherViewmodel.state


    permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
            it[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            viewModel.fetchCurrentWeather()
            dailyweatherViewmodel.fetchDailyWeather()
            hourlyWeatherViewmodel.fetchHourlyWeather()

        }
    }
    DisposableEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        Log.d("Composable", "Permission request launched")
        onDispose { /* Clean up if needed */ }
    }


    if(state.isLoading) {
        CircularProgressBar()
    }
    else if (state.error != null) {
        // Show an error message if there's an error
        Text(
            text = state.error,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
    else{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            item {

                Spacer(modifier = Modifier.height(180.dp))
                CurrentWeathercard(
                    state = state,
                    modifier = Modifier,
                    state1 = state1,
                    currentWeatherViewModel = viewModel
                )
                Spacer(modifier = Modifier.height(10.dp))
                Hourlyweathercomp(state = hourlyWeatherViewmodel.state, modifier = Modifier)
             }

           }



        }
    }

@Composable
fun CircularProgressBar() {
    Box(contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            modifier = Modifier
                .size(70.dp)
                .padding(16.dp)
                .wrapContentSize(Alignment.Center)
        )
    }

}