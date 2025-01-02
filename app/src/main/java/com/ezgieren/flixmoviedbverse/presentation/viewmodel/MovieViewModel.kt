package com.ezgieren.flixmoviedbverse.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ezgieren.flixmoviedbverse.data.model.Genre
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.model.MovieDetails
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private fun <T> initialStateFlow() = MutableStateFlow<Resource<T>>(Resource.Loading())

    private val popularMovies = initialStateFlow<List<Movie>>()
    val trendingMovies = initialStateFlow<List<Movie>>()
    private val genres = initialStateFlow<List<Genre>>()
    val movieDetails = initialStateFlow<MovieDetails>()

    fun fetchPopularMovies() = viewModelScope.launch {
        popularMovies.value = repository.getPopularMovies()
    }

    fun fetchTrendingMovies(timeWindow: String = Constants.ApiParameters.DAY) =
        viewModelScope.launch {
            trendingMovies.value = repository.getTrendingMovies(timeWindow)
        }

    fun fetchGenres() = viewModelScope.launch {
        genres.value = repository.getGenres()
    }

    fun fetchMovieDetails(movieId: Int) = viewModelScope.launch {
        movieDetails.value = repository.getMovieDetails(movieId)
    }

    fun getPagedMovies(category: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { repository.getPagedMovies(category) }
        ).flow.cachedIn(viewModelScope)
    }
}