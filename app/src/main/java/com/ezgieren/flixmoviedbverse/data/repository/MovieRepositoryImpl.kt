package com.ezgieren.flixmoviedbverse.data.repository

import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.remote.TMDBApiService
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDBApiService
) : MovieRepository {
    override suspend fun getPopularMovies(): List<Movie> {
        val response = apiService.getPopularMovies(Constants.API_KEY)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            throw Exception(Constants.ERROR_FETCHING_DATA)
        }
    }
}