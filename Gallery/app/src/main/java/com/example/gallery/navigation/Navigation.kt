package com.example.gallery.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gallery.presentation.screen.FolderScreen
import com.example.gallery.presentation.screen.HomeScreen


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BottomNavGraph(navHostController: NavHostController){
 NavHost(navController =navHostController ,
     startDestination =BottomNaviScreen.Photos.route ){

      composable(BottomNaviScreen.Photos.route){
          HomeScreen(navHostController)
      }

     composable(BottomNaviScreen.Folder.route){
         FolderScreen(navHostController)
     }

 }





 }