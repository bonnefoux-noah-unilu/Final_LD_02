package com.example.lectureexamples.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.lectureexamples.R
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies

@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            //Greeting()
            Text(
                style = MaterialTheme.typography.h6,
                text= "Movie List"
            )
            MyList(navController)
        }
        //MyList()
        //Greeting()
        //WelcomeText(modifier = Modifier.padding(16.dp), text = "welcome to my app!")
    }
}


@Preview
@Composable
fun MyList(navController: NavController = rememberNavController(),
           movies: List<Movie> = getMovies()){
    LazyColumn{
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
        .clickable { onItemClick(movie.id) }
        .animateContentSize(animationSpec = tween(durationMillis = 1000)),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier
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


                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
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
                if(expandedstate) {
                    Text(text = "Director: ${movie.director}\n" +
                            "Release Date: ${movie.year }\n" +
                            "Genre: ${movie.genre}\n" +
                            "Actors: ${movie.actors}\n" +
                            "Rating: ${movie.rating}\n\n" +
                            "Plot: ${movie.plot}\n")


                  }
                }
            }
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
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorites" )
                    Text(" Favorites")
                }
            }
        }
    )
}

@Preview
@Composable
fun WelcomeText(modifier: Modifier = Modifier, text: String = "default") {
    Row(
        modifier = modifier
            .padding(16.dp)
            .background(Color.Blue)
            .fillMaxWidth()
    ) {
        Text(modifier = modifier, text = "Hola")
        Text(text = text)
    }

}

@Preview
@Composable
fun Greeting() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember {
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it},
            label = { Text("Name")}
        )


        /*
        // step 2 - add a mutableStateOf to fire the event for recomposition

       var name = mutableStateOf("")   // use a state holder to register changes
        // var name  by mutableStateOf("")
        Text(text = "Hello ${name.value}!")   // get value of state holder object

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },    // change its value accordingly
            label = { Text("Name")}
        )
        */



        /*
        // step 3 - use remember
        var name by remember {         // use remember to skip overwriting after first composition
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name")}
        )

         */
    }
}