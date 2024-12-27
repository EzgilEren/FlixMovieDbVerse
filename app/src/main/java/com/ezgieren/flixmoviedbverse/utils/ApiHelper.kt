package com.ezgieren.flixmoviedbverse.utils

import retrofit2.Response

suspend fun <T : Any, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    onSuccess: (T) -> R,
    onFailure: (String) -> String
): Resource<R> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Resource.Success(onSuccess(body))
            } else {
                Resource.Error(
                    message = Constants.ErrorMessages.FETCHING_DATA,
                    detailedMessage = Constants.ErrorMessages.GENERIC_ERROR
                )
            }
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
        Resource.Error(
            message = onFailure(
                e.localizedMessage ?: Constants.ErrorMessages.GENERIC_ERROR
            )
        )
    }
}