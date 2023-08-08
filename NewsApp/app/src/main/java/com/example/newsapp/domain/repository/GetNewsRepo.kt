package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.Article_Dto
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface GetNewsRepo {
    suspend fun getNewsArticles(
        category: String
    ):List<Article>



}