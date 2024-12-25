package com.ezgieren.flixmoviedbverse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ezgieren.flixmoviedbverse.data.remote.RetrofitClient
import com.ezgieren.flixmoviedbverse.presentation.view.MovieListScreen
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.theme.FlixMovieDbVerseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlixMovieDbVerseTheme {
                MovieListScreen(viewModel = movieViewModel)
            }
        }

        movieViewModel.fetchMovies()
    }
}

@Composable
fun MovieDataScreen() {
    var movieData by remember { mutableStateOf("Fetching data...") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getPopularMovies(BuildConfig.API_KEY)
                }
                if (response.isSuccessful) {
                    val movies = response.body()?.results?.joinToString("\n") { it.title }
                    movieData = movies ?: "No movies found."
                } else {
                    movieData = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", e.message.toString())
                movieData = "Exception: ${e.message}"
            }
        }
    }
    // Show API response on the screen
    Text(text = movieData)
}