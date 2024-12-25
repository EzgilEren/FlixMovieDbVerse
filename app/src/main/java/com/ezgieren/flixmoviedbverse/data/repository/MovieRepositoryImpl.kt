package com.ezgieren.flixmoviedbverse.data.repository

import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.model.MovieDetailsResponse
import com.ezgieren.flixmoviedbverse.data.remote.TMDBApiService
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDBApiService
) : MovieRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return try {
            val response = apiService.getPopularMovies(Constants.API_KEY)
            if (response.isSuccessful) {
                Resource.Success(response.body()?.results ?: emptyList())
            } else {
                Resource.Error(
                    message = Constants.ErrorMessages.FETCHING_DATA,
                    detailedMessage = Constants.ErrorMessages.detailedError(response.code(), response.message())
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                message = Constants.ErrorMessages.FETCHING_DATA,
                detailedMessage = e.localizedMessage
            )
        }
    }

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsResponse> {
        return try {
            val response = apiService.getMovieDetails(movieId, Constants.API_KEY)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(
                    message = Constants.ErrorMessages.FETCHING_DATA,
                    detailedMessage = Constants.ErrorMessages.detailedError(response.code(), response.message())
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                message = Constants.ErrorMessages.FETCHING_DATA,
                detailedMessage = e.localizedMessage
            )
        }
    }
}