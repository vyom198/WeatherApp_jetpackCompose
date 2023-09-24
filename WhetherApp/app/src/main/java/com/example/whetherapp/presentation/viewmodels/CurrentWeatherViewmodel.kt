package com.example.whetherapp.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.State

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whetherapp.domain.Usecases.GetCurrentWeather


import com.example.whetherapp.domain.location.LocationTracker
import com.example.whetherapp.presentation.states.CurrentWeatherState
import com.example.whetherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private  val context : Context,
    private val locationTracker: LocationTracker,
    private val getCurrentWeather: GetCurrentWeather,

    ) : ViewModel() {

    private val _city = mutableStateOf("")
    var city: State<String> = _city
    var state by mutableStateOf(CurrentWeatherState())
        private set
    @SuppressLint("SuspiciousIndentation")
    fun fetchCurrentWeather(latitude: Double? = null, longitude: Double? = null) {
        viewModelScope.launch {

            state= state.copy(isLoading = true)

            try {
                val location = if (latitude != null && longitude != null) {
                    Pair(latitude, longitude)
                }else {
                    val currentlocation = locationTracker.getLocation()
                    if (currentlocation != null) {
                        Log.d("ViewModel", "Location obtained: $currentlocation")
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
                    _city.value = getCityName(location.first, location.second).toString()
                     Log.d("ViewModel", _city.value)
                    val weatherFlow = getCurrentWeather
                        .invoke(location.first, location.first)
                    Log.d("ViewModel", "Weather data fetching started")

                    weatherFlow.collect { resource->
                        when (resource) {
                            is Resource.Success -> {
                                state = state.copy(isLoading = false, data = resource.data,error = null)
                                Log.d("ViewModel", "Weather data fetched successfully: ${resource.data}")

                            }
                            is Resource.Error -> {
                                state = state.copy(isLoading = false, data = resource.data,error = null)
                                Log.e("ViewModel", "Error fetching weather: ${resource.message}")
                            }

                        }
                    }


            } catch (e: Exception) {
                state = state.copy(isLoading = false, data =null,error = "error fetching weather")
                Log.e("ViewModel", "Error fetching weather: ${e.message}")
            }
        }
    }

    suspend fun getCityName(latitude: Double, longitude: Double): String? {
        return withContext(Dispatchers.IO) {
            val geocoder = Geocoder(  context, Locale.getDefault())
            try {
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                if (addresses?.isNotEmpty() == true) {
                    val address = addresses[0]
                    val city = address.locality
                    city
                    // Return the city name
                } else {
                    null // No addresses found
                }
            } catch (e: Exception) {
                // Handle exceptions, for example, by logging them
                Log.e("Cityname", "Error getting city name: ${e.message}")
                null // Return null on error
            }
        }
    }
}
