package com.ezgieren.flixmoviedbverse.data.remote

import com.ezgieren.flixmoviedbverse.data.model.MovieDetailsResponse
import com.ezgieren.flixmoviedbverse.data.model.PopularMoviesResponse
import com.ezgieren.flixmoviedbverse.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {
    @GET(Constants.Endpoints.POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Response<PopularMoviesResponse>

    @GET(Constants.Endpoints.MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>
}