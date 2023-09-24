package com.example.whetherapp.presentation.navigation

sealed class NavScrren(val name : String, val route: String ){
   object  Home : NavScrren(name ="Home", route="home")
    object  Locations : NavScrren(name="Locations", route="location")
    object  Search : NavScrren(name = "Search", route = "search")

}
