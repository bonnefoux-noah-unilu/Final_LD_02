package com.example.filmappmad.Navigation

sealed class Screen (val route: String){
    object HomeScreen : Screen("home")
    object DetailScreen : Screen("detail/{movieId}")
    object FavoriteScreen : Screen("favorites")
}




