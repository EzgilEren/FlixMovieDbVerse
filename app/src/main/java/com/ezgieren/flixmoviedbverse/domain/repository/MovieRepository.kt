package com.ezgieren.flixmoviedbverse.domain.repository

import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.utils.Resource

interface MovieRepository {
    suspend fun getPopularMovies(): Resource<List<Movie>>
}