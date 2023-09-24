package com.example.whetherapp.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.whetherapp.domain.searchResultModel.Mapper2.maptoCityEntity
import com.example.whetherapp.presentation.navigation.NavScrren
import com.example.whetherapp.presentation.viewmodels.SearchcityViewmodel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocation(navController: NavHostController, viewModel: SearchcityViewmodel) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val searchText by viewModel.searchText.collectAsState()
    val state by viewModel.state.collectAsState()


    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

           LargeTopAppBar(

                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")

                    }
                },
               scrollBehavior = scrollBehavior,
                actions = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {

                        TextField(
                            value = searchText,
                            onValueChange = {
                                viewModel.onSearchTextChange(it)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = {
                                Text("city name..")
                            },
                            singleLine = true,
                            maxLines = 1,


                            )
                        IconButton(
                            onClick = {
                                viewModel.onSearchTextChange("")
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = Color.Gray
                            )
                        }
                    }

                }

            )


        },

        ) {
        if (state.isLoading) {

        } else {


            state.data?.let {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Spacer(modifier = Modifier.height(50.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 16.dp, start = 30.dp)
                    ) {
                        items(it) {

                            Text(text = it.name + "," + it.admin1 + "," + it.country,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .clickable {
                                        val cityEntity = maptoCityEntity(it)
                                        viewModel.insertCity(cityEntity)

                                        navController.popBackStack()
                                        navController.clearBackStack(NavScrren.Search.route)
                                    })


                        }


                    }
                }


            }

        }

        if (state.error != null) {
            Text(
                text = "Error: ${state.error}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.Red
            )
        }
    }
}



