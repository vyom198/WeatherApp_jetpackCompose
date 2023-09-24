package com.example.whetherapp.presentation.viewmodels

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whetherapp.data.location.DefaultLocationTracker
import com.example.whetherapp.domain.Usecases.GetHourlyWeather
import com.example.whetherapp.presentation.states.HourlyWeatherState
import com.example.whetherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HourlyWeatherViewmodel @Inject constructor(
    private  val gethourlyWeather: GetHourlyWeather,
    private  val locationTracker: DefaultLocationTracker
):ViewModel() {

    var state by mutableStateOf(HourlyWeatherState())

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchHourlyWeather(latitude: Double? = null, longitude: Double? = null) {
        viewModelScope.launch {

            state= state.copy(isLoading = true)


                try {
                    val location = if (latitude != null && longitude != null) {
                        Pair(latitude, longitude)
                    } else {
                        val currentlocation = locationTracker.getLocation()
                        if (currentlocation != null) {
                            Log.d("ViewModel2", "Location obtained: $currentlocation")
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
                    val weatherFlow = gethourlyWeather.invoke(location.first,
                        location.second)
                    Log.d("ViewModel2", "Weather data fetching started")

                    weatherFlow.collect { resource->
                        when (resource) {
                            is Resource.Success -> {
                                state = state.copy(isLoading = false, data = resource.data ,error = null)
                                Log.d("ViewModel2", "Weather data fetched successfully: ${resource.data}")

                            }
                            is Resource.Error -> {
                                state = state.copy(isLoading = false, data = resource.data,error = null)
                                Log.e("ViewModel2", "Error fetching weather: ${resource.message}")
                            }


                        }
                    }
                }
             catch (e: Exception) {
                state = state.copy(isLoading = false, data =null,error = "error fetching weather")
                Log.e("ViewModel2", "Error fetching weather: ${e.message}")
            }
        }
    }

}