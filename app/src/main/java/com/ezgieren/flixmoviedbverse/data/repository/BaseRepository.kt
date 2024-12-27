package com.ezgieren.flixmoviedbverse.data.repository

import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import retrofit2.Response

/**
 * Generic function to handle API calls and manage caching with Room.
 *
 * @param apiCall The API request function.
 * @param cacheCall The Room database function for fallback.
 * @param saveToCache Function to save API response to Room.
 * @param transform Function to transform API data to domain data.
 */
suspend fun <T, R> fetchMoviesData(
    apiCall: suspend () -> Response<T>,
    cacheCall: suspend () -> List<R>,
    saveToCache: suspend (List<R>) -> Unit,
    transform: (T?) -> List<R>
): Resource<List<R>> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val transformedData = transform(response.body())
            saveToCache(transformedData)
            Resource.Success(transformedData)
        } else {
            Resource.Error(
                message = Constants.ErrorMessages.FETCHING_DATA,
                detailedMessage = Constants.ErrorMessages.detailedError(
                    response.code(),
                    response.message()
                )
            )
        }
    } catch (e: Exception) {
        val cachedData = cacheCall()
        if (cachedData.isNotEmpty()) {
            Resource.Success(cachedData)
        } else {
            Resource.Error(
                message = Constants.ErrorMessages.FETCHING_DATA,
                detailedMessage = e.localizedMessage ?: Constants.ErrorMessages.GENERIC_ERROR
            )
        }
    }
}