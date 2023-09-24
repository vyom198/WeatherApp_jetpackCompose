package com.example.whetherapp.data.searchmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import java.io.Serializable


@Entity(tableName = "cities")
data class CityEntity (
    @PrimaryKey(autoGenerate = true) var id: Int =0,

    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "population") val population: Int?,
    @ColumnInfo(name = "timezone") val timezone: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "admin1") val admin1: String?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?
)