package com.ezgieren.flixmoviedbverse.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.UIExtensions
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource

@Composable
fun MovieListScreen(viewModel: MovieViewModel) {
    val movieState by viewModel.movieState.collectAsState()

    when (movieState) {
        is Resource.Loading -> UIExtensions.LoadingIndicator()
        is Resource.Success -> {
            val movies = (movieState as Resource.Success<List<Movie>>).data ?: emptyList()
            LazyColumn {
                items(movies) { movie ->
                    Column(modifier = Modifier.padding(16.dp)) {
                        UIExtensions.CustomText(text = movie.title, fontSize = 20, fontWeight = FontWeight.Bold)
                        UIExtensions.MediumSpacer()
                        UIExtensions.CustomText(text = movie.overview)
                        UIExtensions.CustomDivider()
                    }
                }
            }
        }
        is Resource.Error -> {
            val errorMessage = (movieState as Resource.Error<List<Movie>>).message
            UIExtensions.ErrorText(message = errorMessage ?: Constants.ErrorMessages.GENERIC_ERROR)
        }
    }
}