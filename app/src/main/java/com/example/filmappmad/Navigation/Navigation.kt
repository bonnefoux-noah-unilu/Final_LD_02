package com.example.filmappmad.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmappmad.movies.getMovies
import com.example.filmappmad.screens.DetailScreen
import com.example.filmappmad.screens.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController)
        }

        composable(
            route = "detail/{movieId}/{movieTitle}",
            arguments = listOf(navArgument("movieId",){
                type = NavType.StringType
            }
         , navArgument("movieTitle",){
                type = NavType.StringType
            })
        ) {backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"), movieTitle = backStackEntry.arguments?.getString("title"))
        }
    }
}