package com.example.newsapp.presentation.Screen


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List

import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavScreen(
    val name : String,
    val route : String,
    val icon : ImageVector,

    ){
    object Home : BottomNavScreen(
        name = "Home",
        route= "home",
        icon = Icons.Default.Home

    )
    object Article : BottomNavScreen(
        name = "Article",
        route= "article",
        icon = Icons.Filled.List
    )

}
