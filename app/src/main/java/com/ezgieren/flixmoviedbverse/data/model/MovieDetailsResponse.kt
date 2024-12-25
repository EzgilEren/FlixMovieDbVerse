package com.ezgieren.flixmoviedbverse.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("poster_path")
    val posterPath: String?
)

