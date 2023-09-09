package com.example.whetherapp.domain.Usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.whetherapp.data.repository.WeatherRepoImpl
import com.example.whetherapp.domain.model.Daily
import com.example.whetherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.DayOfWeek

import javax.inject.Inject

class GetDailyWeather @Inject constructor(
    private val weatherRepoImpl: WeatherRepoImpl
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun  invoke(lat:Double, lon:Double) :Flow<Resource<Map<DayOfWeek,List<Daily>>>> = flow{
        try {
            emit(Resource.Success(weatherRepoImpl.getDailyWeather(lat , lon)))
        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }
    }
}