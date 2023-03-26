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
import com.example.filmappmad.screens.FavoriteScreen
import com.example.filmappmad.screens.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId",){
                type = NavType.StringType
            }
         )
        ) {backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }

        composable(route = Screen.FavoriteScreen.route) {
            FavoriteScreen(navController)
        }
    }
}