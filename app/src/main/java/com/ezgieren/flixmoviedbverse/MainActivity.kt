package com.ezgieren.flixmoviedbverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.UIExtensions
import com.ezgieren.flixmoviedbverse.ui.theme.FlixMovieDbVerseTheme
import com.ezgieren.flixmoviedbverse.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlixMovieDbVerseTheme {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieTabs(movieViewModel)
                }
            }
        }
    }

    @Composable
    fun MovieTabs(viewModel: MovieViewModel) {
        val tabs = listOf(
            Constants.MovieCategories.POPULAR,
            Constants.MovieCategories.TOP_RATED,
            Constants.MovieCategories.UPCOMING,
            Constants.MovieCategories.NOW_PLAYING,
            Constants.MovieCategories.TRENDING
        )
        val selectedTabIndex = remember { androidx.compose.runtime.mutableIntStateOf(0) }

        Column {
            UIExtensions.CustomTabRow(
                tabs = tabs,
                selectedTabIndex = selectedTabIndex.intValue,
                onTabSelected = { selectedTabIndex.intValue = it }
            )
            when (selectedTabIndex.intValue) {
                0 -> UIExtensions.MovieListWithPagination(Constants.MovieCategories.POPULAR, viewModel)
                1 -> UIExtensions.MovieListWithPagination(Constants.MovieCategories.TOP_RATED, viewModel)
                2 -> UIExtensions.MovieListWithPagination(Constants.MovieCategories.UPCOMING, viewModel)
                3 -> UIExtensions.MovieListWithPagination(Constants.MovieCategories.NOW_PLAYING, viewModel)
                4 -> UIExtensions.MovieListWithPagination(Constants.MovieCategories.TRENDING, viewModel)
            }
        }
    }
}