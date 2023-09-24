package com.example.whetherapp.data.network_and_dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whetherapp.data.searchmodel.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Query("SELECT * FROM cities")
    fun getAllCities(): Flow<List<CityEntity>>

    @Delete
    suspend fun deleteCity(city: CityEntity)
}