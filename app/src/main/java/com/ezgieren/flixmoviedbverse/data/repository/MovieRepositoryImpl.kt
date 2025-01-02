package com.ezgieren.flixmoviedbverse.data.repository

import androidx.paging.PagingSource
import com.ezgieren.flixmoviedbverse.data.local.MovieDao
import com.ezgieren.flixmoviedbverse.data.model.Genre
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.model.MovieDetails
import com.ezgieren.flixmoviedbverse.data.model.SpokenLanguage
import com.ezgieren.flixmoviedbverse.data.paging.MoviePagingSource
import com.ezgieren.flixmoviedbverse.data.remote.TMDBApiService
import com.ezgieren.flixmoviedbverse.domain.repository.MovieRepository
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import com.ezgieren.flixmoviedbverse.utils.safeApiCall
import com.ezgieren.flixmoviedbverse.utils.toDomain
import com.ezgieren.flixmoviedbverse.utils.toEntity
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDBApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    // Fetch popular movies with caching support
    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return fetchMoviesData(
            apiCall = { apiService.getPopularMovies() },
            cacheCall = { movieDao.getAllMovies().map { it.toDomain() } },
            saveToCache = { movieDao.insertMovies(it.map { movie -> movie.toEntity() }) },
            transform = { response -> response?.results ?: emptyList() }
        )
    }

    // Fetch top-rated movies with caching support
    override suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return fetchMoviesData(
            apiCall = { apiService.getTopRatedMovies() },
            cacheCall = { movieDao.getAllMovies().map { it.toDomain() } },
            saveToCache = { movieDao.insertMovies(it.map { movie -> movie.toEntity() }) },
            transform = { response -> response?.results ?: emptyList() }
        )
    }

    // Fetch upcoming movies
    override suspend fun getUpcomingMovies(): Resource<List<Movie>> {
        return fetchMoviesData(
            apiCall = { apiService.getUpcomingMovies() },
            cacheCall = { movieDao.getAllMovies().map { it.toDomain() } },
            saveToCache = { movieDao.insertMovies(it.map { movie -> movie.toEntity() }) },
            transform = { response -> response?.results ?: emptyList() }
        )
    }

    // Fetch now playing movies
    override suspend fun getNowPlayingMovies(): Resource<List<Movie>> {
        return fetchMoviesData(
            apiCall = { apiService.getNowPlayingMovies() },
            cacheCall = { movieDao.getAllMovies().map { it.toDomain() } },
            saveToCache = { movieDao.insertMovies(it.map { movie -> movie.toEntity() }) },
            transform = { response -> response?.results ?: emptyList() }
        )
    }

    // Fetch trending movies
    override suspend fun getTrendingMovies(timeWindow: String): Resource<List<Movie>> {
        return safeApiCall(
            apiCall = { apiService.getTrendingMovies(timeWindow = timeWindow, page = 1) },
            onSuccess = { it.results },
            onFailure = { Constants.ErrorMessages.GENERIC_ERROR }
        )
    }

    // Fetch movie details by ID
    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {
        return safeApiCall(
            apiCall = { apiService.getMovieDetails(movieId) },
            onSuccess = { it },
            onFailure = { Constants.ErrorMessages.GENERIC_ERROR }
        )
    }

    // Fetch genres
    override suspend fun getGenres(): Resource<List<Genre>> {
        return safeApiCall(
            apiCall = { apiService.getGenres() },
            onSuccess = { it.genres }, // GenresResponse -> List<Genre>
            onFailure = { Constants.ErrorMessages.GENERIC_ERROR }
        )
    }

    // Fetch spoken languages
    override suspend fun getLanguages(): Resource<List<SpokenLanguage>> {
        return safeApiCall(
            apiCall = { apiService.getLanguages() },
            onSuccess = { it },
            onFailure = { Constants.ErrorMessages.GENERIC_ERROR }
        )
    }

    // Get paging movies
    override fun getPagedMovies(category: String): PagingSource<Int, Movie> {
        return MoviePagingSource(apiService, category)
    }
}