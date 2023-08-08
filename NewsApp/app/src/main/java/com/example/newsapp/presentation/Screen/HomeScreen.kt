@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.presentation.Screen

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.R

import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.Screen.viewmodel.newsViewmodel



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

    fun HomeScreen( navHostController: NavHostController,viewModel: newsViewmodel = hiltViewModel()
    ) {
        val res = viewModel.articles.value
    var searchQuery by remember { mutableStateOf("") }

    val categories = listOf(
        "General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment"
    )



    res.data?.let { articleList ->
        // Filter the articleList based on the searchQuery
        val filteredArticles = if (searchQuery.isNotBlank()) {
            articleList.filter { article ->
                article.title?.contains(searchQuery, ignoreCase = true)
                    ?: article.description?.contains(searchQuery, ignoreCase = true) ?: false
            }
        } else {
            articleList
        }

        if (res.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            }
        }
        if (res.error?.isNotBlank() == true) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = res.error.toString(), modifier = Modifier.align(Alignment.Center))
            }
        }
        res.data?.let { articleList ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {

                                TextField(
                                    value = searchQuery,
                                    onValueChange = { searchQuery = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clip(shape = CircleShape),
                                    singleLine = true,
                                    placeholder = { Text(text = "Search News") }
                                )


                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) {
                        CategoryChip(Category = it, onSelectedChange =
                        {  selected,it->
                            if (selected) {

                                viewModel.loadNewsbyCategory(it)


                            }


                        }

                        )
                    }

                }






                            Box(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxSize()
                            ) {
                                if(searchQuery==null){
                                    LazyColumn {
                                        items(articleList) { article ->
                                            ArticleItem(article, navHostController, viewModel)
                                        }
                                    }
                                }
                                else{


                                    LazyColumn {
                                        items(filteredArticles) { article ->
                                            ArticleItem(article, navHostController, viewModel)
                                        }

                                    }


                                }





                            }



                        }



                }

             }

        }





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(article: Article,navHostController: NavHostController,
         viewModel: newsViewmodel       ) {

    ElevatedCard(
        onClick = {
              viewModel.setArticle(article)

               navHostController.navigate(BottomNavScreen.Article.route){
                   launchSingleTop = true
                   popUpTo(BottomNavScreen.Home.route) { inclusive = false }
               }

        },
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
            .padding(top = 20.dp, end = 10.dp, start = 10.dp)
            .background(color = Color.Transparent),
        shape = RectangleShape,
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = article.urlToImage),
            contentDescription = "articleImage",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillBounds
        )

        Text(text = article.title.toString(),
            modifier = Modifier.fillMaxWidth(), fontSize = 18.sp)





    }






}
