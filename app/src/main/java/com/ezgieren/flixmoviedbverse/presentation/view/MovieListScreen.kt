package com.ezgieren.flixmoviedbverse.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel

@Composable
fun MovieListScreen(viewModel: MovieViewModel) {
    val movies by viewModel.movies.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    if (errorMessage.isNotEmpty()) {
        Text(text = errorMessage)
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(movies) { movieTitle ->
                Text(text = movieTitle)
            }
        }
    }
}