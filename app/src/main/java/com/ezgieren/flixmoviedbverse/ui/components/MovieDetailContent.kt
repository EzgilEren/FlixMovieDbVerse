package com.ezgieren.flixmoviedbverse.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomTabRow
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.ErrorText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.LoadingIndicator
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource

@Composable
fun MovieTabs(viewModel: MovieViewModel, navController: NavHostController) {
    val tabs = Constants.MovieCategories.getAllCategories()
    val selectedTabIndex = remember { mutableIntStateOf(0) }

    Column {
        CustomTabRow(
            tabs = tabs,
            selectedTabIndex = selectedTabIndex.intValue,
            onTabSelected = { selectedTabIndex.intValue = it }
        )

        LaunchedEffect(selectedTabIndex.intValue) {
            val selectedTab = tabs[selectedTabIndex.intValue]
            if (selectedTab == Constants.MovieCategories.TRENDING) {
                viewModel.fetchTrendingMovies()
            }
        }

        when (tabs[selectedTabIndex.intValue]) {
            Constants.MovieCategories.TRENDING -> TrendingMovies(
                viewModel = viewModel,
                isHolidayTheme = false,
                onMovieClick = { movieId ->
                    navController.navigate("${Constants.NavRoutes.MOVIE_DETAILS}/$movieId")
                }
            )

            else -> MovieListWithPagination(
                category = tabs[selectedTabIndex.intValue],
                viewModel = viewModel,
                onMovieClick = { movieId ->
                    navController.navigate("${Constants.NavRoutes.MOVIE_DETAILS}/$movieId")
                }
            )
        }
    }
}

@Composable
fun TrendingMovies(
    viewModel: MovieViewModel,
    isHolidayTheme: Boolean,
    onMovieClick: (Int) -> Unit
) {
    val moviesState = viewModel.trendingMovies.collectAsState()

    when (val movies = moviesState.value) {
        is Resource.Loading -> {
            LoadingIndicator()
        }

        is Resource.Error -> {
            ErrorText(message = movies.message.orEmpty())
        }

        is Resource.Success -> {
            val moviesList = movies.data ?: emptyList()
            LazyColumn {
                items(moviesList.size) { index ->
                    val movie = moviesList[index]
                    MovieCard(
                        movie = movie,
                        trend = if (index == 0) Constants.GeneralMessages.TRENDING_IN_2024 else "",
                        isHolidayTheme = isHolidayTheme,
                        onClick = onMovieClick
                    )
                }
            }
        }
    }
}

// **Pagination using Paging 3**
@Composable
fun MovieListWithPagination(
    category: String,
    viewModel: MovieViewModel,
    onMovieClick: (Int) -> Unit
) {
    val movies = viewModel.getPagedMovies(category).collectAsLazyPagingItems()

    LazyColumn {
        items(movies.itemCount) { index ->
            val movie = movies[index]
            movie?.let {
                MovieCard(
                    movie = it,
                    isHolidayTheme = false,
                    onClick = onMovieClick
                )
            }
        }

        when (movies.loadState.refresh) {
            is androidx.paging.LoadState.Loading -> item {
                LoadingIndicator()
            }

            is androidx.paging.LoadState.Error -> item {
                ErrorText(message = Constants.ErrorMessages.FETCHING_DATA)
            }

            else -> Unit
        }
    }
}