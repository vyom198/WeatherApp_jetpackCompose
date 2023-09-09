package com.example.newsapp.presentation.Screen

import android.util.Log
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.presentation.Screen.viewmodel.newsViewmodel

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavScreen.Home.route){
        composable(BottomNavScreen.Home.route){
           HomeScreen(navController)
        }
        composable(BottomNavScreen.Article.route) {backStackEntry->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(BottomNavScreen.Home.route)
            }
            val parentViewModel = hiltViewModel<newsViewmodel>(parentEntry)
            detailedNews(parentViewModel)



        }


    }
}



