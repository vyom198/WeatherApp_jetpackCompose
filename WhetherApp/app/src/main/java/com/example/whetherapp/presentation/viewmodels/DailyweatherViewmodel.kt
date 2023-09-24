
package com.example.whetherapp.presentation.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whetherapp.domain.Usecases.GetDailyWeather
import com.example.whetherapp.domain.location.LocationTracker
import com.example.whetherapp.presentation.states.DailyweatherState
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
  fun fetchDailyWeather(latitude: Double? = null, longitude: Double? = null) {
    viewModelScope.launch {

      state = state.copy(isLoading = true)

      try {
        val location = if (latitude != null && longitude != null) {
          Pair(latitude, longitude)
        } else {
          val currentlocation = locationTracker.getLocation()
          if (currentlocation != null) {
            Log.d("ViewModel1", "Location obtained: $currentlocation")
            Pair(currentlocation.latitude, currentlocation.longitude)
          } else {
            state = state.copy(
              isLoading = false,
              data = null,
              error = "Location not available"
            )
            return@launch
          }
        }

        val weatherFlow = getDailyWeather.invoke(location.first,
          location.second)
        Log.d("ViewModel1", "Weather data fetching started")

        weatherFlow.collect { resource ->
          when (resource) {
            is Resource.Success -> {
              state = state.copy(isLoading = false, data = resource.data, error = null)
              Log.d("ViewModel1", "Weather data fetched successfully: ${resource.data?.entries}")

            }

            is Resource.Error -> {
              state = state.copy(isLoading = false, data = resource.data, error = null)
              Log.e("ViewModel1", "Error fetching weather: ${resource.message}")
            }


          }
        }

      } catch (e: Exception) {
        state = state.copy(isLoading = false, data = null, error = "error fetching weather")
        Log.e("ViewModel1", "Error fetching weather: ${e.message}")
      }

    }
  }
}