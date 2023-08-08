package com.example.newsapp.data.repository

import com.example.newsapp.data.network.ApiInterface
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.GetNewsRepo
import com.example.newsapp.util.SafeApiRequest
import javax.inject.Inject

class GetNewsRepoIMpl @Inject constructor(
    private val apiService : ApiInterface
):GetNewsRepo, SafeApiRequest() {


    override suspend fun getNewsArticles(category: String): List<Article> {
           val response = safeApiRequest {
               apiService.getAllNews("in",category) }
        return response.articles?.map {
           Article(
               author = it.author,
               content = it.content,
               description = it.description,
               publishedAt = it.publishedAt,
               source = it.source,
               title = it.title,
               urlToImage = it.urlToImage,
               url = it.url



           )
        }!!
    }





}