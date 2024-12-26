package com.ezgieren.flixmoviedbverse.data.repository

import com.ezgieren.flixmoviedbverse.data.local.MovieDao
import com.ezgieren.flixmoviedbverse.data.model.Genre
import com.ezgieren.flixmoviedbverse.data.model.Language
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.model.PopularMoviesResponse
import com.ezgieren.flixmoviedbverse.data.remote.TMDBApiService
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import com.ezgieren.flixmoviedbverse.utils.toDomain
import com.ezgieren.flixmoviedbverse.utils.toEntity
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDBApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return fetchMovies { apiService.getPopularMovies() }
    }

    override suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return fetchMovies { apiService.getTopRatedMovies() }
    }

    override suspend fun getUpcomingMovies(): Resource<List<Movie>> {
        return fetchMovies { apiService.getUpcomingMovies() }
    }

    override suspend fun getNowPlayingMovies(): Resource<List<Movie>> {
        return fetchMovies { apiService.getNowPlayingMovies() }
    }

    override suspend fun getTrendingMovies(): Resource<List<Movie>> {
        return fetchMovies { apiService.getTrendingMovies() }
    }

    override suspend fun getGenres(): Resource<List<Genre>> {
        return try {
            val response = apiService.getGenres()
            if (response.isSuccessful) {
                Resource.Success(response.body()?.genres ?: emptyList())
            } else {
                Resource.Error(Constants.ErrorMessages.FETCHING_DATA)
            }
        } catch (e: Exception) {
            Resource.Error(Constants.ErrorMessages.FETCHING_DATA, e.localizedMessage)
        }
    }

    override suspend fun getLanguages(): Resource<List<Language>> {
        return try {
            val response = apiService.getLanguages()
            if (response.isSuccessful) {
                Resource.Success(response.body() ?: emptyList())
            } else {
                Resource.Error(Constants.ErrorMessages.FETCHING_DATA)
            }
        } catch (e: Exception) {
            Resource.Error(Constants.ErrorMessages.FETCHING_DATA, e.localizedMessage)
        }
    }
    private suspend fun fetchMovies(
        apiCall: suspend () -> Response<PopularMoviesResponse>
    ): Resource<List<Movie>> {
        return try {
            // API çağrısı
            val response = apiCall()
            if (response.isSuccessful) {
                val movies = response.body()?.results ?: emptyList()

                // Room'a kaydet
                movieDao.insertMovies(movies.map { it.toEntity() })

                Resource.Success(movies)
            } else {
                // API başarısızsa hata döndür
                Resource.Error(
                    message = Constants.ErrorMessages.FETCHING_DATA,
                    detailedMessage = Constants.ErrorMessages.detailedError(response.code(), response.message())
                )
            }
        } catch (e: Exception) {
            // API başarısızsa Room'dan veri çek
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