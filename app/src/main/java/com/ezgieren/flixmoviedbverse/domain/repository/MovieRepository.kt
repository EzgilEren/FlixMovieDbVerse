package com.ezgieren.flixmoviedbverse.domain.repository

import com.ezgieren.flixmoviedbverse.data.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
}