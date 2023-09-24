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


import androidx.compose.foundation.layout.Spacer

import androidx.compose.ui.Alignment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.whetherapp.presentation.CurrentWeatherViewModel
import com.example.whetherapp.presentation.viewmodels.DailyweatherViewmodel
import com.example.whetherapp.presentation.viewmodels.HourlyWeatherViewmodel
import com.example.whetherapp.presentation.navigation.NavScrren
import com.example.whetherapp.presentation.viewmodels.SearchcityViewmodel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllWeatherComposable( navController: NavHostController,
                          viewModel: CurrentWeatherViewModel ,
                          dailyweatherViewmodel: DailyweatherViewmodel ,
                          hourlyWeatherViewmodel: HourlyWeatherViewmodel ,
                          searchcityViewmodel: SearchcityViewmodel,
                          handle:SavedStateHandle

) {


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    val state = viewModel.state
    val state1 = dailyweatherViewmodel.state
    var permissionStatus by remember { mutableStateOf(false) }


    val selectedLatitude = searchcityViewmodel.selectedLatitude.value
    Log.d("selectedLat", selectedLatitude.toString())
    val selectedLongitude = searchcityViewmodel.selectedLongitude.value
    Log.d("selectedLong", selectedLongitude.toString())
   var switchState = searchcityViewmodel.switchState
    Log.d("toggle", switchState.toString())

    var refreshWeather by remember { mutableStateOf(false) }


    permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // Check if both permissions are granted
        val granted =
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (granted) {
            permissionStatus = true
            switchState = true
            Log.d("toggle", switchState.toString())
        }
    }

    LaunchedEffect(permissionStatus) {
        if (!permissionStatus) {
            // Permissions are not granted, request them
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }
    LaunchedEffect( switchState,refreshWeather,selectedLatitude, selectedLongitude, permissionStatus) {
        // Check if location permissions are granted and a location is selected
        if (refreshWeather || selectedLatitude != null && selectedLongitude != null) {
            // Fetch weather data for the selected location
            viewModel.fetchCurrentWeather(selectedLatitude, selectedLongitude)
            dailyweatherViewmodel.fetchDailyWeather(selectedLatitude, selectedLongitude)
            hourlyWeatherViewmodel.fetchHourlyWeather(selectedLatitude, selectedLongitude)
        } else if ( refreshWeather||permissionStatus || switchState) {
            // If no location is selected, fetch weather data for the current location
            viewModel.fetchCurrentWeather()
            dailyweatherViewmodel.fetchDailyWeather()
            hourlyWeatherViewmodel.fetchHourlyWeather()
        }
        refreshWeather = false
       
    }

    if (state.isLoading) {

        CircularProgressBar()

    } else if (state.error != null) {
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
    } else {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    title = {

                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(NavScrren.Locations.route)}) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "settings",

                                )
                        }
                        IconButton(onClick = {
                            refreshWeather = true
                        }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "refresh",

                                )
                        }


                    },
                    scrollBehavior = scrollBehavior
                )

            }
        ) {


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

