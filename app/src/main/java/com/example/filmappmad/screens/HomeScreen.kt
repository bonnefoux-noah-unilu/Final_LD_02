package com.example.filmappmad.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.filmappmad.movies.Movie
import com.example.filmappmad.movies.getMovies


@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {

            MyList(navController)
        }
        //MyList()
        //Greeting()
        //WelcomeText(modifier = Modifier.padding(16.dp), text = "welcome to my app!")
    }
}

@Composable
fun TopAppBar() {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "Movies") },
        actions = {
            IconButton(onClick = { expanded = true }) {

                Icon(Icons.Filled.MoreVert, contentDescription = "Kebab menu")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false}
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }, modifier = Modifier.width(130.dp)) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorites" )
                    Text(" Favorites")

                }
            }
        }
    )
}


@Preview
@Composable
fun MyList(navController: NavController = rememberNavController(),
           movies: List<Movie> = getMovies()){
    LazyColumn{
        item { TopAppBar() }
        items(movies) {movie ->
            MovieRow(
                movie = movie,
            )  { movieId ->
                Log.d("MyList", "item clicked $movieId")
                // navigate to detailscreen
                navController.navigate("detail/$movieId")
            }
        }
    }
}


@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var expandedstate by remember { mutableStateOf(false) }
    val rotationstate by animateFloatAsState(targetValue = if (expandedstate) 180f else 0f)

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { onItemClick(movie.title) }
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp

    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                val painter = rememberImagePainter(data = movie.images[0],
                    builder = {
                    })
                Image(
                    painter = painter,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites"
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    movie.title, style = MaterialTheme.typography.h6,
                    modifier = Modifier


                )
                IconButton(
                    modifier = Modifier
                        .padding(5.dp)
                        .rotate(rotationstate),


                    onClick = { expandedstate = !expandedstate }

                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Expand",

                        )
                }


            }
            if (expandedstate) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {

                    Text(
                        text = "Director: ${movie.director}\n" +
                                "Release Date: ${movie.year}\n" +
                                "Genre: ${movie.genre}\n" +
                                "Actors: ${movie.actors}\n" +
                                "Rating: ${movie.rating}",
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    )

                    Divider(
                        color = Color.Gray, thickness = 1.dp, modifier = Modifier
                            .width(1.dp)
                            .padding(5.dp)
                    )

                }

                Divider(
                    color = Color.Gray, thickness = 1.dp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "\nPlot: ${movie.plot}",
                        fontSize = MaterialTheme.typography.subtitle1.fontSize
                    )
                }


            }
        }
    }
}





