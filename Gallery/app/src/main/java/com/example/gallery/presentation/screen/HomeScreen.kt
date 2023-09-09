package com.example.gallery.presentation.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.gallery.domain.entity.Image
import com.example.gallery.presentation.allimages.AllimageViewmodel
import dagger.hilt.android.lifecycle.HiltViewModel

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeScreen(navHostController: NavHostController,

              // viewmodel: AllimageViewmodel = hiltViewModel()
             ) {

 //var uiState = viewmodel._uiState
    //Log.d("HomeScreen", "Number of images: ${uiState.imageList.size}")
    Column(
        modifier = Modifier.padding(
            start =  12.dp,
            end = 12.dp
        )
    ) {
        LazyVerticalGrid(columns =GridCells.Fixed(3)  ){

  //        items(uiState.imageList.size){ index->
//             val image = uiState.imageList[index]
//                Log.d("HomeScreen2", "Loading image: ${image.path}")
//            Image(
//                    painter = rememberImagePainter(image.path), // Example: Use a library like Coil for image loading
//                    contentDescription = null, // Add appropriate description
//                    modifier = Modifier.size(180.dp)
//                )
//            }

        }

    }

}