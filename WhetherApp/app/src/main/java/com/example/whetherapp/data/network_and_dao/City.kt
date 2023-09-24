package com.example.whetherapp.data.network_and_dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whetherapp.data.searchmodel.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
}

