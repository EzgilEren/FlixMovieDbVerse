package com.ezgieren.flixmoviedbverse.utils

import com.ezgieren.flixmoviedbverse.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.API_KEY
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    // Database
    object Database {
        const val MOVIES_TABLE = "movies"
        const val DATABASE_NAME = "flixmoviedbverse_db"
    }

    // Query constants
    object QueryConstants {
        const val SELECT_ALL_MOVIES = "SELECT * FROM movies"
        const val DELETE_ALL_MOVIES = "DELETE FROM movies"
    }

    // API Endpoints
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

        fun getAllCategories(): List<String> {
            return listOf(POPULAR, TOP_RATED, UPCOMING, NOW_PLAYING, TRENDING)
        }
    }

    // Nav Routes
    object NavRoutes {
        const val MOVIE_LIST = "movie_list"
        const val MOVIE_DETAILS = "movie_details"
    }

    // Error Messages
    object ErrorMessages {
        const val FETCHING_DATA = "An error occurred while fetching data."
        const val GENERIC_ERROR = "Something went wrong. Please try again later."
        fun detailedError(code: Int, message: String): String {
            return "Error Code: $code, Message: $message"
        }
    }

    object MovieDetails {
        const val TITLE = "Title: "
        const val RELEASE_DATE = "Release Date: "
        const val OVERVIEW = "Overview: "
        const val GENRES = "Genres: "
        const val LANGUAGE = "Language: "
        const val POSTER_PATH = "Poster Path: "
    }

    object UiConstants {
        const val DEFAULT_PADDING = 16
        const val CORNER_RADIUS = 12
    }

    // General Messages
    object GeneralMessages {
        const val LOADING = "Loading movies..."
        const val DETAILS = "Movie Details"
        const val FAVORITE_MOVIES_2024 = "\uD83C\uDF89🌟 Favorite Movies of 2024"
        const val TRENDING_IN_2024 = "Trending in 2024"
        const val HOLIDAY_THEME_ACTIVE = "🎄 Holiday Theme Active"
        const val NORMAL_THEME_ACTIVE = "✨ Normal Theme Active"
        const val UNKNOWN_TITLE = "Unknown Title"
        const val NO_OVERVIEW = "No overview available."
        const val BACK = "Back"
        const val MOVIE_DETAILS = "Movie Details"
        const val POSTER_DESCRIPTION = "Poster for %s"
    }
}