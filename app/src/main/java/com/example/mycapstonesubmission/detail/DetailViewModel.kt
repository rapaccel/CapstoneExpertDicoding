package com.example.mycapstonesubmission.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycapstonesubmission.core.domain.model.Movies
import com.example.mycapstonesubmission.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun setFavoriteMovies(movies: Movies, newStatus:Boolean) =
        viewModelScope.launch {
            moviesUseCase.setFavoriteMovies(movies, newStatus)
        }
}   