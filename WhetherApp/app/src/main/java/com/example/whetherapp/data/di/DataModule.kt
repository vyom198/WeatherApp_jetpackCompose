package com.example.whetherapp.data.di

import android.app.Application
import com.example.whetherapp.data.network.Weatherapi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

@Provides
@Singleton
    fun WeatherapiService():Weatherapi{
        return Retrofit.Builder().baseUrl("https://api.open-meteo.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(
                Weatherapi::class.java
            )

    }
    @Provides
    @Singleton
    fun provideFusedlocationClient(app:Application):FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(app)
    }



}