package com.ezgieren.flixmoviedbverse.di

import com.ezgieren.flixmoviedbverse.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrlBuilder = originalUrl.newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)

        if (originalUrl.encodedPath.contains("movie") &&
            !originalUrl.encodedPath.contains("genre")) {
            newUrlBuilder.addQueryParameter("page", "1")
        }

        val newRequest = originalRequest.newBuilder()
            .url(newUrlBuilder.build())
            .build()

        return chain.proceed(newRequest)
    }
}