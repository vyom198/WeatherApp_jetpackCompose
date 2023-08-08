package com.example.newsapp.presentation.Screen.viewmodel

import androidx.compose.runtime.State

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.use_case.GetNewsUseCase
import com.example.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.newsapp.data.model.Article_Dto

@HiltViewModel
class newsViewmodel @Inject constructor( val getNewsUseCase: GetNewsUseCase):ViewModel() {
    var articles = mutableStateOf(HomeStateHolder())
    private val _selectedArticle = mutableStateOf<Article?>(null)
    val selectedArticle: State<Article?> = _selectedArticle

    private val _savedArticles = mutableStateOf<List<Article_Dto>>(emptyList())
    val savedArticles: State<List<Article_Dto>> = _savedArticles


    private  var category =
         mutableStateOf("General")


     private var _category = category.value

    var selectedCategory = _category


    init {
        when (_category) {
            "Entertainment" -> ProvideArticles(_category)
            "Sports" -> ProvideArticles(_category)
            "Business"-> ProvideArticles( _category)
            "Health"-> ProvideArticles(_category)
            "Science"-> ProvideArticles(_category)
            "Technology"-> ProvideArticles(_category)
            // Add more cases for other categories if needed
            else -> ProvideArticles(_category)
        }
   }

    fun loadNewsbyCategory(Category:String){
        ProvideArticles(Category)
    }

    fun ProvideArticles(category: String) {
        getNewsUseCase(category).onEach {

            when (it) {
                is Resource.Loading -> {

                    articles.value = HomeStateHolder(isLoading = true)

                }

                is Resource.Success -> {


                    articles.value = HomeStateHolder(data = it.data)
                }

                is Resource.Error -> {

                    articles.value = HomeStateHolder(error = it.message.toString())
                }

            }

        }.launchIn(viewModelScope)
    }

 fun setArticle(article: Article){
     _selectedArticle.value = article
 }



}