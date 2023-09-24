package com.example.whetherapp.domain.Usecases

import com.example.whetherapp.data.repository.SearchRepoImpl
import com.example.whetherapp.data.repository.WeatherRepoImpl
import com.example.whetherapp.data.searchmodel.CityEntity
import com.example.whetherapp.domain.searchResultModel.Search_Results
import com.example.whetherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetSearchResults  @Inject constructor(private val repoImpl: SearchRepoImpl){

    operator  fun invoke( cityname : String ): Flow<Resource<List<Search_Results>>> = flow {

        try {
            emit(Resource.Success(repoImpl.GetSearchResutls(cityname)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }

    }

      suspend operator fun invoke(city: CityEntity) {
       repoImpl.insertCity(city)
    }


    operator fun invoke(): Flow<List<CityEntity>> {
       return repoImpl.getAllCities()
    }

}