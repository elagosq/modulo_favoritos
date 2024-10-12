package com.cursokotlin.myfavoritelist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cursokotlin.myfavoritelist.views.FavoritesScreen
import com.cursokotlin.myfavoritelist.views.HomeScreen

@Composable
fun NavManager(){
   val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "HomeScreen"){
      composable("HomeScreen"){
        HomeScreen(navController)
      }
      composable("FavoritesScreen"){
          FavoritesScreen(navController)
      }
    }
}