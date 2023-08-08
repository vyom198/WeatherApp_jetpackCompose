package com.example.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters


data class Article_Dto(
    val id : Long = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source_Dto,
    val title: String,
    val url: String,
    val urlToImage: String
)