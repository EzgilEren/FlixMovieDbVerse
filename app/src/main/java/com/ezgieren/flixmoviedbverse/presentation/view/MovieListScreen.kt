package com.ezgieren.flixmoviedbverse.presentation.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource

@Composable
fun MovieListScreen(viewModel: MovieViewModel) {
    val movieState by viewModel.movieState.collectAsState()

    when (movieState) {
        is Resource.Loading -> Text(text = Constants.GeneralMessages.LOADING)
        is Resource.Success -> {
            val movies = (movieState as Resource.Success<List<Movie>>).data ?: emptyList()
            LazyColumn {
                items(movies) { movie ->
                    Text(text = movie.title)
                }
            }
        }
        is Resource.Error -> {
            val message = (movieState as Resource.Error<List<Movie>>).message
            val details = (movieState as Resource.Error<List<Movie>>).detailedMessage
            Text(text = "$message${Constants.GeneralMessages.DETAILS}\n$details")
        }
    }
}