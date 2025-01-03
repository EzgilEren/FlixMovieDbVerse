package com.ezgieren.flixmoviedbverse.domain.repository

import androidx.paging.PagingSource
import com.ezgieren.flixmoviedbverse.data.model.Genre
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.model.MovieDetails
import com.ezgieren.flixmoviedbverse.data.model.SpokenLanguage
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource

interface MovieRepository {
    suspend fun getPopularMovies(): Resource<List<Movie>>
    suspend fun getTopRatedMovies(): Resource<List<Movie>>
    suspend fun getUpcomingMovies(): Resource<List<Movie>>
    suspend fun getNowPlayingMovies(): Resource<List<Movie>>
    suspend fun getTrendingMovies(timeWindow: String = Constants.ApiParameters.DAY): Resource<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails>
    suspend fun getGenres(): Resource<List<Genre>>
    suspend fun getLanguages(): Resource<List<SpokenLanguage>>
    fun getPagedMovies(category: String): PagingSource<Int, Movie>
}