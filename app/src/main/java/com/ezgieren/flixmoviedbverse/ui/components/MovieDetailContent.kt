package com.ezgieren.flixmoviedbverse.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.UIExtensions
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.ErrorText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.LoadingIndicator
import com.ezgieren.flixmoviedbverse.ui.theme.AppColors
import com.ezgieren.flixmoviedbverse.ui.theme.ChristmasColors
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource

@Composable
fun MovieTabs(
    viewModel: MovieViewModel,
    navController: NavHostController
) {
    val tabs = Constants.MovieCategories.getAllCategories()
    val selectedTabIndex = remember { mutableStateOf(0) }

    Column {
        UIExtensions.CustomTabRow(
            tabs = tabs,
            selectedTabIndex = selectedTabIndex.value,
            onTabSelected = { selectedTabIndex.value = it }
        )

        val selectedTab = tabs[selectedTabIndex.value]
        when (selectedTab) {
            Constants.MovieCategories.TRENDING -> TrendingMovies(viewModel, navController)
            else -> MovieListWithPagination(
                category = selectedTab,
                viewModel = viewModel,
                onMovieClick = { movieId ->
                    navController.navigate("${Constants.NavRoutes.MOVIE_DETAILS}/$movieId")
                }
            )
        }
    }
}

@Composable
fun TrendingMovies(viewModel: MovieViewModel, navController: NavHostController) {
    val moviesState = viewModel.popularMovies.collectAsState()

    when (val movies = moviesState.value) {
        is Resource.Loading -> {
            LoadingIndicator()
        }

        is Resource.Error -> {
            ErrorText(message = movies.message.orEmpty())
        }

        is Resource.Success -> {
            val moviesList = movies.data ?: emptyList()
            Column {
                // Favorite Movies Section
                FavoriteMoviesSection(
                    movies = moviesList.take(5),
                    isHolidayTheme = true,
                    onMovieClick = { movieId ->
                        navController.navigate("${Constants.NavRoutes.MOVIE_DETAILS}/$movieId")
                    }
                )
                LazyColumn {
                    items(moviesList.size) { index ->
                        val movie = moviesList[index]
                        MovieCard(
                            movie = movie,
                            trend = Constants.GeneralMessages.TRENDING_IN_2024,
                            isHolidayTheme = true,
                            onClick = { navController.navigate("${Constants.NavRoutes.MOVIE_DETAILS}/${movie.id}") }
                        )
                    }
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

@Composable
fun FavoriteMoviesSection(
    movies: List<Movie>,
    isHolidayTheme: Boolean,
    onMovieClick: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        CustomText(
            text = Constants.GeneralMessages.FAVORITE_MOVIES_2024,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = if (isHolidayTheme) ChristmasColors.HolidayPrimary else AppColors.Primary
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(movies.size) { index ->
                MovieCard(
                    movie = movies[index],
                    trend = Constants.GeneralMessages.TRENDING_IN_2024,
                    isHolidayTheme = isHolidayTheme,
                    onClick = onMovieClick
                )
            }
        }
    }
}