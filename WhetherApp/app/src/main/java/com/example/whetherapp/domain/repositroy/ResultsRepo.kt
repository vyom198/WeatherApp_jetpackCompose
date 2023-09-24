package com.example.whetherapp.domain.repositroy

import com.example.whetherapp.data.searchmodel.CityEntity
import com.example.whetherapp.domain.searchResultModel.Search_Results
import kotlinx.coroutines.flow.Flow


interface ResultsRepo {

    suspend fun GetSearchResutls(cityName : String): List<Search_Results>


    // City management functions
    suspend fun insertCity(city: CityEntity)

    suspend fun deleteCity(city: CityEntity)

    fun getAllCities(): Flow<List<CityEntity>>
}