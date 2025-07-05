package com.example.mycapstonesubmission.core.domain.usecase

import com.example.mycapstonesubmission.core.data.UiState
import com.example.mycapstonesubmission.core.data.source.remote.response.ResultsItem
import com.example.mycapstonesubmission.core.domain.model.Movies
import com.example.mycapstonesubmission.core.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesRepository: IMoviesRepository) : MoviesUseCase  {
    override fun getAllMovies() = moviesRepository.getAllMovies()
    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return moviesRepository.getFavoriteMovies()
    }

    override suspend fun setFavoriteMovies(
        movies: Movies,
        state: Boolean
    ) {
       return moviesRepository.setFavoriteMovies(movies, state)
    }

}