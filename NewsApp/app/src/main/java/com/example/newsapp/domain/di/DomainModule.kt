package com.example.newsapp.domain.di


import com.example.newsapp.data.network.ApiInterface
import com.example.newsapp.data.repository.GetNewsRepoIMpl
import com.example.newsapp.domain.repository.GetNewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
     fun ProvideNewsRepo (apiInterface: ApiInterface ):GetNewsRepo{
     return GetNewsRepoIMpl(apiService = apiInterface)


     }


}