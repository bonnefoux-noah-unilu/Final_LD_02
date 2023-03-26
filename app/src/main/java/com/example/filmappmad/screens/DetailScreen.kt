package com.example.filmappmad.screens

import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, movieId: String?, movieTitle: String?) {

  @Composable
  fun TopAppBar2() {
    TopAppBar(
      title = { Text(text = "$movieTitle") },
      actions = {
        IconButton(onClick = { navController.popBackStack() }) {

          Icon(Icons.Default.ArrowBack, contentDescription = "ArrowBack")
        }

      }
    )
  }

  var expanded by remember { mutableStateOf(false) }
  Scaffold(
    topBar = {
      TopAppBar2()
    },
    content = {
      Text(text = movieId.toString())
    }
  )


}