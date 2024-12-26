package com.ezgieren.flixmoviedbverse.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ezgieren.flixmoviedbverse.utils.Constants

@Dao
interface MovieDao {
    @Query(Constants.QueryConstants.SELECT_ALL_MOVIES)
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query(Constants.QueryConstants.DELETE_ALL_MOVIES)
    suspend fun clearMovies()
}