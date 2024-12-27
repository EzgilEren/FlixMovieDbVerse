package com.ezgieren.flixmoviedbverse.data.remote

import com.ezgieren.flixmoviedbverse.di.ApiKeyInterceptor
import com.ezgieren.flixmoviedbverse.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor()) // Added Interceptor
        .build()

    val instance: TMDBApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient) // OkHttpClient integration
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApiService::class.java)
    }
}