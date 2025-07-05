package com.example.mycapstonesubmission.core.domain.usecase

import com.example.mycapstonesubmission.core.data.UiState
import com.example.mycapstonesubmission.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies() : Flow<UiState<List<Movies>>>
    fun getFavoriteMovies() : Flow<List<Movies>>
   suspend fun setFavoriteMovies(movies: Movies, state: Boolean)

}