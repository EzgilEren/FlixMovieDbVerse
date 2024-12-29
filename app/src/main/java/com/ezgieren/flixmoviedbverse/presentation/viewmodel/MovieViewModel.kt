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
import com.ezgieren.flixmoviedbverse.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val popularMovies: StateFlow<Resource<List<Movie>>> = _popularMovies

    private val _genres = MutableStateFlow<Resource<List<Genre>>>(Resource.Loading())
    val genres: StateFlow<Resource<List<Genre>>> = _genres

    private val _movieDetails = MutableStateFlow<Resource<MovieDetails>>(Resource.Loading())
    val movieDetails: StateFlow<Resource<MovieDetails>> = _movieDetails

    // Fetch Popular Movies
    fun fetchPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = repository.getPopularMovies()

        }
    }

    // Fetch Genres
    fun fetchGenres() {
        viewModelScope.launch {
            _genres.value = repository.getGenres()
        }
    }

    // Fetch Movie Details
    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = repository.getMovieDetails(movieId)
        }
    }

    // Get paging movies
    fun getPagedMovies(category: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { repository.getPagedMovies(category) }
        ).flow.cachedIn(viewModelScope)
    }
}