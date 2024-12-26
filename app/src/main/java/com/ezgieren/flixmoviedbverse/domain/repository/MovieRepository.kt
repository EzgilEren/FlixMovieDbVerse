package com.ezgieren.flixmoviedbverse.domain.repository

import com.ezgieren.flixmoviedbverse.data.model.Genre
import com.ezgieren.flixmoviedbverse.data.model.Language
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.utils.Resource

interface MovieRepository {
    suspend fun getPopularMovies(): Resource<List<Movie>>
    suspend fun getTopRatedMovies(): Resource<List<Movie>>
    suspend fun getUpcomingMovies(): Resource<List<Movie>>
    suspend fun getNowPlayingMovies(): Resource<List<Movie>>
    suspend fun getTrendingMovies(): Resource<List<Movie>>
    suspend fun getGenres(): Resource<List<Genre>>
    suspend fun getLanguages(): Resource<List<Language>>
}