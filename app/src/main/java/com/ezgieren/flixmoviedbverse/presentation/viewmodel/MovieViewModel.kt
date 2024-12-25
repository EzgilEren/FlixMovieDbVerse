package com.ezgieren.flixmoviedbverse.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
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

    fun fetchMovies() {
        viewModelScope.launch {
            _movieState.value = Resource.Loading()
            _movieState.value = repository.getPopularMovies()
        }
    }
}