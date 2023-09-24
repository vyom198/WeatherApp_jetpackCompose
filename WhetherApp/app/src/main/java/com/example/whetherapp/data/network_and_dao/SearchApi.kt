package com.example.whetherapp.data.network_and_dao

import com.example.whetherapp.data.searchmodel.SearchResults_Dto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
  @GET("/v1/search?count=10&language=en&format=json")
  suspend fun GetSearchresults(
     @Query("name")
     cityName : String

  ):Response<SearchResults_Dto>


}