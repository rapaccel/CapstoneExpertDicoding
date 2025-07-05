package com.example.mycapstonesubmission.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mycapstonesubmission.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    val favoriteMovies = moviesUseCase.getFavoriteMovies().asLiveData()
}