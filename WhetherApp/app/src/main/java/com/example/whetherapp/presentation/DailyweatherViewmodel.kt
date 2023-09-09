
package com.example.whetherapp.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whetherapp.data.location.DefaultLocationTracker
import com.example.whetherapp.domain.Usecases.GetCurrentWeather
import com.example.whetherapp.domain.Usecases.GetDailyWeather
import com.example.whetherapp.domain.location.LocationTracker
import com.example.whetherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DailyweatherViewmodel @Inject constructor(
  private val locationTracker: LocationTracker,
  private val getDailyWeather: GetDailyWeather
):ViewModel() {
  var state by mutableStateOf(DailyweatherState())
    private set

  @RequiresApi(Build.VERSION_CODES.O)
  fun fetchDailyWeather() {
    viewModelScope.launch {

      state= state.copy(isLoading = true)

      try {
        val location = locationTracker.getLocation()

        if (location != null) {
          Log.d("ViewModel1", "Location obtained: $location")

          val weatherFlow = getDailyWeather.invoke(location.latitude, location.longitude)
          Log.d("ViewModel1", "Weather data fetching started")

          weatherFlow.collect { resource->
            when (resource) {
              is Resource.Success -> {
                state = state.copy(isLoading = false, data = resource.data,error = null)
                Log.d("ViewModel1", "Weather data fetched successfully: ${resource.data?.entries}")

              }
              is Resource.Error -> {
                state = state.copy(isLoading = false, data = resource.data,error = null)
                Log.e("ViewModel1", "Error fetching weather: ${resource.message}")
              }


            }
          }
        } else  {
          state = state.copy(isLoading = false, data = null,error = "Location not available")
          Log.e("ViewModel1", "Location not available")
        }
      } catch (e: Exception) {
        state = state.copy(isLoading = false, data =null,error = "error fetching weather")
        Log.e("ViewModel1", "Error fetching weather: ${e.message}")
      }
    }
  }

}