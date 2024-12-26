package com.ezgieren.flixmoviedbverse.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.flixmoviedbverse.data.model.Genre
import com.ezgieren.flixmoviedbverse.data.model.Language
import com.ezgieren.flixmoviedbverse.data.model.Movie
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

    private val _movieState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val movieState: StateFlow<Resource<List<Movie>>> get() = _movieState
    val languages = MutableLiveData<Resource<List<Language>>>()
    val genres = MutableLiveData<Resource<List<Genre>>>()

    fun fetchMovies() {
        viewModelScope.launch {
            _movieState.value = Resource.Loading()
            _movieState.value = repository.getPopularMovies()
        }
    }
    fun fetchTopRatedMovies() {
        viewModelScope.launch {
            _movieState.value = Resource.Loading()
            _movieState.value = repository.getTopRatedMovies()
        }
    }

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            _movieState.value = Resource.Loading()
            _movieState.value = repository.getUpcomingMovies()
        }
    }

    fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            _movieState.value = Resource.Loading()
            _movieState.value = repository.getNowPlayingMovies()
        }
    }

    fun fetchTrendingMovies() {
        viewModelScope.launch {
            _movieState.value = Resource.Loading()
            _movieState.value = repository.getTrendingMovies()
        }
    }
    fun fetchLanguages() {
        viewModelScope.launch {
            languages.value = repository.getLanguages()
        }
    }

    fun fetchGenres() {
        viewModelScope.launch {
            genres.value = repository.getGenres()
        }
    }
}