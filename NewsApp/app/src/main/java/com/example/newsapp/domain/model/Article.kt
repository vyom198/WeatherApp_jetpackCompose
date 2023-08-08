package com.example.newsapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.data.model.Source_Dto

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source_Dto?,
    val title: String?,
    val url: String?,
    val urlToImage: String?


)
