package com.ezgieren.flixmoviedbverse.data.remote

import com.ezgieren.flixmoviedbverse.data.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val popularMovies : String = "movie/popular"

interface TMDBApiService {
    @GET(popularMovies)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ) : Response<PopularMoviesResponse>
}