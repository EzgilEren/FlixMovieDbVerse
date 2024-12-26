package com.ezgieren.flixmoviedbverse.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ezgieren.flixmoviedbverse.utils.Constants

@Entity(tableName = Constants.Database.MOVIES_TABLE)
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String
)