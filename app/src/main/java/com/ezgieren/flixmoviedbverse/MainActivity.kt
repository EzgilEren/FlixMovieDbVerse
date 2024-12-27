package com.ezgieren.flixmoviedbverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.theme.FlixMovieDbVerseTheme
import com.ezgieren.flixmoviedbverse.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlixMovieDbVerseTheme {
                Column {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TestAllApiCalls(movieViewModel)
                    }
                }
            }
        }

        // Trigger API calls
        movieViewModel.fetchPopularMovies()
        movieViewModel.fetchGenres()
        movieViewModel.fetchMovieDetails(550)
    }
}
@Composable
fun TestAllApiCalls(viewModel: MovieViewModel) {
    val popularMoviesState by viewModel.popularMovies.collectAsState()
    val genresState by viewModel.genres.collectAsState()
    val movieDetailsState by viewModel.movieDetails.collectAsState()

    Column {
        // Popular Movies
        when (val state = popularMoviesState) {
            is Resource.Loading -> {
                Text("Loading Popular Movies...")
            }
            is Resource.Success -> {
                Text("Popular Movies: ${state.data?.joinToString { it.title }}")
            }
            is Resource.Error -> {
                Text("Error: ${state.message}")
            }
        }

        // Genres
        when (val state = genresState) {
            is Resource.Loading -> {
                Text("Loading Genres...")
            }
            is Resource.Success -> {
                Text("Genres: ${state.data?.joinToString { it.name }}")
            }
            is Resource.Error -> {
                Text("Error: ${state.message}")
            }
        }

        // Movie Details
        when (val state = movieDetailsState) {
            is Resource.Loading -> {
                Text("Loading Movie Details...")
            }
            is Resource.Success -> {
                Text("Movie Details: ${state.data?.title}")
            }
            is Resource.Error -> {
                Text("Error: ${state.message}")
            }
        }
    }
}