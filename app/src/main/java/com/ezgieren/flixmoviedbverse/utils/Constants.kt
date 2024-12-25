package com.ezgieren.flixmoviedbverse.utils

import com.ezgieren.flixmoviedbverse.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.API_KEY

    // Error Message
    const val ERROR_FETCHING_DATA = "Error fetching data"
    const val ERROR_NO_MOVIES = "No movies available"

    object Endpoints {
        const val POPULAR_MOVIES = "movie/popular"
    }
}