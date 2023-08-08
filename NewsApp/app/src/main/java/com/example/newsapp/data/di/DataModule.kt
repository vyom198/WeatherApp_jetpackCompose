package com.example.newsapp.data.di

import com.example.newsapp.data.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)

object DataModule {
    @Provides
    fun ProvideApiService():ApiInterface {
        return Retrofit.Builder().baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }



}




