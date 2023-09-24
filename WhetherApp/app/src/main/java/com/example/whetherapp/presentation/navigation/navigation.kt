package com.example.whetherapp.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whetherapp.presentation.CurrentWeatherViewModel
import com.example.whetherapp.presentation.composables.AllWeatherComposable
import com.example.whetherapp.presentation.composables.LocationList
import com.example.whetherapp.presentation.composables.SearchLocation
import com.example.whetherapp.presentation.viewmodels.DailyweatherViewmodel
import com.example.whetherapp.presentation.viewmodels.HourlyWeatherViewmodel
import com.example.whetherapp.presentation.viewmodels.SearchcityViewmodel

@SuppressLint("RememberReturnType", "SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(   ) {

val navController = rememberNavController()
    val savedStateHandle = remember {
        SavedStateHandle()
    }

    val searchcityViewmodel: SearchcityViewmodel = hiltViewModel()
   val  currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
    val dailyweatherViewmodel: DailyweatherViewmodel = hiltViewModel()
   val  hourlyWeatherViewmodel: HourlyWeatherViewmodel = hiltViewModel()

    NavHost(navController = navController, startDestination = NavScrren.Home.route){

  composable(NavScrren.Home.route){backStackEntry->
   AllWeatherComposable(navController,searchcityViewmodel= searchcityViewmodel, handle = savedStateHandle
            , viewModel = currentWeatherViewModel, dailyweatherViewmodel = dailyweatherViewmodel,
             hourlyWeatherViewmodel = hourlyWeatherViewmodel)
       }

      composable(NavScrren.Locations.route){backStackEntry->
      LocationList(handle = savedStateHandle, navController = navController, searchcityViewmodel = searchcityViewmodel)

             }


     composable(NavScrren.Search.route){backStackEntry->
         SearchLocation(navController,searchcityViewmodel)
  }

 }



}

