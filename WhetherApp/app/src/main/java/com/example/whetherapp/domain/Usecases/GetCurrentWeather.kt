package com.example.whetherapp.domain.Usecases

import com.example.whetherapp.data.repository.WeatherRepoImpl
import com.example.whetherapp.domain.model.CurrentWeather
import com.example.whetherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(private val weatherRepoImpl: WeatherRepoImpl) {

operator fun invoke(lat:Double, lon:Double): Flow<Resource<CurrentWeather>> = flow {
    try {
        emit(Resource.Success(weatherRepoImpl.getCurrentWeather(lat, lon)))
    } catch (e: Exception) {
        emit(Resource.Error(e.message))
    }

}
}
