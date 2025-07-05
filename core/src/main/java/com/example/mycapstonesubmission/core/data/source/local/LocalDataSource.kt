package com.example.mycapstonesubmission.core.data.source.local

import com.example.mycapstonesubmission.core.data.source.local.entity.MoviesEntity
import com.example.mycapstonesubmission.core.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val moviesDao: MoviesDao) {
    fun getAllMovies(): Flow<List<MoviesEntity>> = moviesDao.getAllMovies()
    fun getFavoriteMovies(): Flow<List<MoviesEntity>> = moviesDao.getFavoriteMovies()
    suspend fun insertMovies(movies: List<MoviesEntity>) = moviesDao.insertMovies(movies)
    fun setFavoriteMovies(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        moviesDao.updateFavoriteMovies(movies)
    }
}