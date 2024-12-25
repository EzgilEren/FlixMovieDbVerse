package com.ezgieren.flixmoviedbverse.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<String>>(emptyList())
    val movies: StateFlow<List<String>> get() = _movies

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val movieList = repository.getPopularMovies()
                _movies.value = movieList.map { it.title }
            } catch (e: Exception) {
                _errorMessage.value = Constants.ERROR_FETCHING_DATA
            }
        }
    }
}