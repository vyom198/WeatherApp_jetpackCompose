package com.example.newsapp.domain.use_case

import com.example.newsapp.data.model.Article_Dto
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.GetNewsRepo
import com.example.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private  val getNewsRepo: GetNewsRepo
) {
    operator fun invoke(category: String) : Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading(""))
        try{
            emit(Resource.Success(getNewsRepo.getNewsArticles(category)))

        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }

    }




}