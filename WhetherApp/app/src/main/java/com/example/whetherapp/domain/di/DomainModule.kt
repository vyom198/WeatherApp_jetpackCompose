package com.example.whetherapp.domain.di

import com.example.whetherapp.data.network.Weatherapi
import com.example.whetherapp.data.repository.WeatherRepoImpl
import com.example.whetherapp.domain.repositroy.WeatherRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherapi: Weatherapi
    ): WeatherRepo{
        return WeatherRepoImpl(weatherapi)
    }
}