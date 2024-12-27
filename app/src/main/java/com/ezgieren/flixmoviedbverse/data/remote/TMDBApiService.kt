package com.ezgieren.flixmoviedbverse.data.remote

import com.ezgieren.flixmoviedbverse.data.model.GenresResponse
import com.ezgieren.flixmoviedbverse.data.model.MovieDetails
import com.ezgieren.flixmoviedbverse.data.model.PopularMoviesResponse
import com.ezgieren.flixmoviedbverse.data.model.SpokenLanguage
import com.ezgieren.flixmoviedbverse.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApiService {
    @GET(Constants.Endpoints.POPULAR_MOVIES)
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>

    @GET(Constants.Endpoints.TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(): Response<PopularMoviesResponse>

    @GET(Constants.Endpoints.UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(): Response<PopularMoviesResponse>

    @GET(Constants.Endpoints.NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(): Response<PopularMoviesResponse>

    @GET(Constants.Endpoints.TRENDING_MOVIES)
    suspend fun getTrendingMovies(): Response<PopularMoviesResponse>

    @GET(Constants.Endpoints.MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path(Constants.ApiParameters.MOVIE_ID) movieId: Int
    ): Response<MovieDetails>

    @GET(Constants.Endpoints.GENRES)
    suspend fun getGenres(): Response<GenresResponse>

    @GET(Constants.Endpoints.LANGUAGES)
    suspend fun getLanguages(): Response<List<SpokenLanguage>>

    //TODO: delete this method created fot testing
    @GET(Constants.Endpoints.MOVIE_DETAILS)
    suspend fun testGetMovieDetails(
        @Path("movie_id") movieId: Int
    ): Response<ResponseBody>
}