package com.ezgieren.flixmoviedbverse.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String = "Unknown Title",
    @SerializedName("overview")
    val overview: String = "No overview available",
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String = "N/A"
)