package com.ezgieren.flixmoviedbverse.data.repository

import com.ezgieren.flixmoviedbverse.data.local.MovieDao
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.remote.TMDBApiService
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import com.ezgieren.flixmoviedbverse.utils.toDomain
import com.ezgieren.flixmoviedbverse.utils.toEntity
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDBApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return try {
            val response = apiService.getPopularMovies(Constants.API_KEY)
            if (response.isSuccessful) {
                val movies = response.body()?.results ?: emptyList()
                // Save to Room
                movieDao.insertMovies(movies.map { it.toEntity() })
                Resource.Success(movies)
            } else {
                Resource.Error(
                    message = Constants.ErrorMessages.FETCHING_DATA,
                    detailedMessage = Constants.ErrorMessages.detailedError(response.code(), response.message())
                )
            }
        } catch (e: Exception) {
            // Fetch from Room if API fails
            val cachedMovies = movieDao.getAllMovies().map { it.toDomain() }
            if (cachedMovies.isNotEmpty()) {
                Resource.Success(cachedMovies)
            } else {
                Resource.Error(
                    message = Constants.ErrorMessages.FETCHING_DATA,
                    detailedMessage = e.localizedMessage
                )
            }
        }
    }
}