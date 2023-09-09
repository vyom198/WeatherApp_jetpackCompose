package com.example.gallery.navigation

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.gallery.R

sealed class BottomNaviScreen(
    val name: String,
    val route: String,
    val icon:  @Composable () -> Unit
){
object Photos : BottomNaviScreen("Photos","photos",
    icon = {
        Icon(painter = painterResource(id = R.drawable.outline_image_24), contentDescription =null )
    }
)

    object Folder: BottomNaviScreen("Folder","folder",
        icon = {
            Icon(painter = painterResource(id = R.drawable.outline_folder_24), contentDescription =null )
        }
    )

}
