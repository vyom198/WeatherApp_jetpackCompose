package com.example.newsapp.data.network

import com.example.newsapp.data.model.All_Articles_Dto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/v2/top-headlines")
    suspend fun getAllNews(
        @Query("country")
        countryName:String = "in"
        , @Query("category") category: String,
        @Query("apiKey")
        apiKey:String = "0efefad5a1144c0eb4cdc35e6aebad35"

    ):Response<All_Articles_Dto>

}