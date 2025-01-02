package com.ezgieren.flixmoviedbverse.data.remote

import com.ezgieren.flixmoviedbverse.data.model.GenresResponse
import com.ezgieren.flixmoviedbverse.data.model.MovieDetails
import com.ezgieren.flixmoviedbverse.data.model.PopularMoviesResponse
import com.ezgieren.flixmoviedbverse.data.model.SpokenLanguage
import com.ezgieren.flixmoviedbverse.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {
    @GET(Constants.ApiEndpoints.POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(Constants.ApiParameters.PAGE) page: Int = 1
    ): Response<PopularMoviesResponse>

    @GET(Constants.ApiEndpoints.TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(
        @Query(Constants.ApiParameters.PAGE) page: Int = 1
    ): Response<PopularMoviesResponse>

    @GET(Constants.ApiEndpoints.UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(
        @Query(Constants.ApiParameters.PAGE) page: Int = 1
    ): Response<PopularMoviesResponse>

    @GET(Constants.ApiEndpoints.NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(
        @Query(Constants.ApiParameters.PAGE) page: Int = 1
    ): Response<PopularMoviesResponse>

    @GET(Constants.ApiEndpoints.TRENDING_MOVIES)
    suspend fun getTrendingMovies(
        @Path(Constants.ApiParameters.TIME_WINDOW) timeWindow: String = Constants.ApiParameters.DAY,
        @Query(Constants.ApiParameters.PAGE) page: Int = 1
    ): Response<PopularMoviesResponse>

    @GET(Constants.ApiEndpoints.MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path(Constants.ApiParameters.MOVIE_ID) movieId: Int
    ): Response<MovieDetails>

    @GET(Constants.ApiEndpoints.GENRES)
    suspend fun getGenres(): Response<GenresResponse>

    @GET(Constants.ApiEndpoints.LANGUAGES)
    suspend fun getLanguages(): Response<List<SpokenLanguage>>
}