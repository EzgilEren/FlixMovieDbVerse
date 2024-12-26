package com.ezgieren.flixmoviedbverse.utils

import com.ezgieren.flixmoviedbverse.data.local.MovieEntity
import com.ezgieren.flixmoviedbverse.data.model.Movie

// Movie to Entity Conversion
fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate
    )
}

// Entity to Domain Model Conversion
fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate
    )
}