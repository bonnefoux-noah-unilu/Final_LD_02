package com.example.filmappmad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.TopAppBar
import com.example.filmappmad.Navigation.Navigation
import com.example.filmappmad.ui.theme.FilmAppMADTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmAppMADTheme {
                Navigation()

            }
        }
    }
}


