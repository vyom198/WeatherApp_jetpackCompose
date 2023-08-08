package com.example.newsapp.presentation.Screen

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.Screen.viewmodel.newsViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChip(
    Category: String,  onSelectedChange: (Boolean,String) -> Unit
) {

 var selected by remember {
     mutableStateOf(false)
 }
    ElevatedFilterChip(
        selected = selected,
        onClick = {onSelectedChange(!selected,Category)

                  },
        label = { Text(text = Category)},
        colors = FilterChipDefaults.elevatedFilterChipColors(),
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,

                    contentDescription = "selected",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )

            }
        } else null
    )
}






