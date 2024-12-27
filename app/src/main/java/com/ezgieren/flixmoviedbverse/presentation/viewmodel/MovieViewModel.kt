package com.ezgieren.flixmoviedbverse.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.flixmoviedbverse.data.model.*
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
            _popularMovies.value = Resource.Loading()
            val result = repository.getPopularMovies()
            _popularMovies.value = result
        }
    }

    // Fetch Genres
    fun fetchGenres() {
        viewModelScope.launch {
            _genres.value = Resource.Loading()
            val result = repository.getGenres()
            _genres.value = result
        }
    }

    // Fetch Movie Details
    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = Resource.Loading()
            val result = repository.getMovieDetails(movieId)
            _movieDetails.value = result
        }
    }
}