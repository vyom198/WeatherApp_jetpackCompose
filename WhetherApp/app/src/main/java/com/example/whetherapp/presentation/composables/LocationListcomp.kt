package com.example.whetherapp.presentation.composables

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row



import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController

import com.example.whetherapp.presentation.navigation.NavScrren

import com.example.whetherapp.presentation.viewmodels.SearchcityViewmodel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationList(navController: NavHostController,
                 handle: SavedStateHandle,
                 searchcityViewmodel: SearchcityViewmodel
) {



    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val allCities by searchcityViewmodel.allCities.collectAsState(emptyList())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

            LargeTopAppBar(
                title = { Text(text = "Locations")},
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(NavScrren.Home.route)
                     }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")

                    }

                },
                scrollBehavior = scrollBehavior

            )
            
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(NavScrren.Search.route)}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add new location")
            }
        }

    ) {


         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(10.dp),
             horizontalAlignment = Alignment.CenterHorizontally,

         ) {



             Spacer(modifier = Modifier.height(200.dp))
             SwitchWithIcon(searchcityViewmodel)
             LazyColumn(
                 modifier = Modifier
                     .align(Alignment.CenterHorizontally)
                     .padding(top = 50.dp),
                 flingBehavior = ScrollableDefaults.flingBehavior(),
                 userScrollEnabled = true

             ){
                 items(allCities) {

                     Row(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = Arrangement.SpaceBetween,
                         verticalAlignment = Alignment.CenterVertically
                     ) {

                         RadioButton(
                             selected = searchcityViewmodel.selectedCityid== it.id, onClick = {
                                 searchcityViewmodel.selectedCityid= it.id
                                 searchcityViewmodel.selectedLatitude.value = it.latitude
                                 searchcityViewmodel.selectedLongitude.value = it.longitude





                             }, modifier =
                             Modifier.padding(start = 19.dp)
                         )
                         Text(text = it.name + "," + it.admin1, fontSize = 19.sp)



                     IconButton(onClick = { searchcityViewmodel.deleteCity(city = it) }) {
                         Icon(
                             imageVector = Icons.Default.Clear,
                             modifier = Modifier.padding(end = 15.dp),
                             contentDescription = "deletecity"
                         )

                     }
                 }
                     }


                 }
             Divider(
                 modifier = Modifier
                     .padding(20.dp)
                     .fillMaxWidth()
                     .height(2.dp),
                 color = Color.LightGray,
             )

             }
         }
      


    }



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SwitchWithIcon(
    searchcityViewmodel: SearchcityViewmodel,

    ) {


     Row(
         horizontalArrangement = Arrangement.SpaceBetween,
         verticalAlignment = Alignment.CenterVertically,

         modifier = Modifier
             .clip(RoundedCornerShape(20.dp))
             .background(color = Color.Blue)
             .fillMaxWidth()
             .padding(20.dp)
             .toggleable(
                 value =searchcityViewmodel.switchState ,
                 onValueChange = {

                     searchcityViewmodel.toggleSwitchState(it)

                 },
                 role = Role.Switch,
             )
     ) {

             Text(text = "Current Location", fontSize = 20.sp)
             Switch(checked = searchcityViewmodel.switchState, onCheckedChange = null)


         }


}
