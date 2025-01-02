package com.ezgieren.flixmoviedbverse.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.data.remote.TMDBApiService
import com.ezgieren.flixmoviedbverse.utils.Constants

class MoviePagingSource(
    private val apiService: TMDBApiService,
    private val category: String,
    private val timeWindow: String = Constants.ApiParameters.DAY // default "day"
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = when (category) {
                Constants.MovieCategories.POPULAR -> apiService.getPopularMovies(page)
                Constants.MovieCategories.TOP_RATED -> apiService.getTopRatedMovies(page)
                Constants.MovieCategories.UPCOMING -> apiService.getUpcomingMovies(page)
                Constants.MovieCategories.NOW_PLAYING -> apiService.getNowPlayingMovies(page)
                Constants.MovieCategories.TRENDING -> apiService.getTrendingMovies(timeWindow, page)
                else -> throw IllegalArgumentException("${Constants.MovieCategories.INVALID_CATEGORY}: $category")
            }
            val movies = response.body()?.results.orEmpty()
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}