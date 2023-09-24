package com.example.whetherapp.presentation.states

import com.example.whetherapp.domain.model.Daily
import java.time.DayOfWeek

data class DailyweatherState(
    val isLoading : Boolean= false,
    val data : Map<DayOfWeek,List<Daily>>?  = null,
    val error: String? = null
)
