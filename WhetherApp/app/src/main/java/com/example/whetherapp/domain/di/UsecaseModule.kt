package com.example.whetherapp.domain.di

import com.example.whetherapp.data.repository.WeatherRepoImpl
import com.example.whetherapp.domain.Usecases.GetCurrentWeather
import com.example.whetherapp.domain.Usecases.GetDailyWeather
import com.example.whetherapp.domain.Usecases.GetHourlyWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UsecaseModule {
@Provides
fun providegetCurrentWeather( weatherRepoImpl: WeatherRepoImpl):GetCurrentWeather{
    return GetCurrentWeather(weatherRepoImpl)
}
    @Provides
    fun providegetDailyWeather (weatherRepoImpl: WeatherRepoImpl):GetDailyWeather{
    return GetDailyWeather(weatherRepoImpl)
}


    @Provides
    fun provideHourlyWeather(weatherRepoImpl: WeatherRepoImpl):GetHourlyWeather{
        return GetHourlyWeather(weatherRepoImpl)
    }

}