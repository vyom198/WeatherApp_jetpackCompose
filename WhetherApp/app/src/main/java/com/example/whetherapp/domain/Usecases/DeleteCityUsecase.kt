package com.example.whetherapp.domain.Usecases

import com.example.whetherapp.data.repository.SearchRepoImpl
import com.example.whetherapp.data.searchmodel.CityEntity
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
import javax.inject.Inject

class DeleteCityUsecase @Inject constructor(
   private val repoImpl: SearchRepoImpl
) {
    suspend operator fun invoke(city: CityEntity) {
        repoImpl.deleteCity(city)
    }
}