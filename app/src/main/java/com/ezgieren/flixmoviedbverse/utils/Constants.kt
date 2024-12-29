package com.ezgieren.flixmoviedbverse.utils

import com.ezgieren.flixmoviedbverse.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.API_KEY

    // Database
    object Database {
        const val MOVIES_TABLE = "movies"
        const val DATABASE_NAME = "flixmoviedbverse_db"
    }

    object QueryConstants {
        const val SELECT_ALL_MOVIES = "SELECT * FROM movies"
        const val DELETE_ALL_MOVIES = "DELETE FROM movies"
    }

    // Endpoints
    object Endpoints {
        const val POPULAR_MOVIES = "movie/popular"
        const val MOVIE_DETAILS = "movie/{movie_id}"
        const val TOP_RATED_MOVIES = "movie/top_rated"
        const val UPCOMING_MOVIES = "movie/upcoming"
        const val NOW_PLAYING_MOVIES = "movie/now_playing"
        const val TRENDING_MOVIES = "trending/movie/day"
        const val GENRES = "genre/movie/list"
        const val LANGUAGES = "configuration/languages"
    }

    object ApiParameters {
        const val PAGE = "page"
        const val MOVIE_ID = "movie_id"
    }

    //Movie Categories
    object MovieCategories {
        const val POPULAR = "Popular"
        const val TOP_RATED = "Top Rated"
        const val UPCOMING = "Upcoming"
        const val NOW_PLAYING = "Now Playing"
        const val TRENDING = "Trending"
    }

    // Error Messages
    object ErrorMessages {
        const val FETCHING_DATA = "An error occurred while fetching data."
        const val NO_MOVIES = "No movies available."
        const val GENERIC_ERROR = "Something went wrong. Please try again later."
        fun detailedError(code: Int, message: String): String {
            return "Error Code: $code, Message: $message"
        }
    }

    object UiConstants {
        const val DEFAULT_PADDING = 16
        const val CORNER_RADIUS = 12
    }

    // General Messages
    object GeneralMessages {
        const val LOADING = "Loading movies..."
        const val DETAILS = "\nDetails:"
    }
}