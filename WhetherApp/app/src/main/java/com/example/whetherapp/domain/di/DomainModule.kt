package com.example.whetherapp.domain.di

import androidx.lifecycle.SavedStateHandle
import com.example.whetherapp.data.network_and_dao.CityDao
import com.example.whetherapp.data.network_and_dao.SearchApi
import com.example.whetherapp.data.network_and_dao.Weatherapi
import com.example.whetherapp.data.repository.SearchRepoImpl
import com.example.whetherapp.data.repository.WeatherRepoImpl
import com.example.whetherapp.domain.repositroy.ResultsRepo
import com.example.whetherapp.domain.repositroy.WeatherRepo
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
    ): WeatherRepo {
        return WeatherRepoImpl(weatherapi)

    }
        @Provides
        @Singleton
        fun  provideSearchRepository(
            searchApi: SearchApi, cityDao: CityDao
        ) : ResultsRepo{return SearchRepoImpl(searchApi,cityDao)}


}