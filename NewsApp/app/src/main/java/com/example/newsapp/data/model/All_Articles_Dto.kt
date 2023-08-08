package com.example.newsapp.data.model

data class All_Articles_Dto(
    val articles: List<Article_Dto>?,
    val status: String?,
    val totalResults: Int?
)