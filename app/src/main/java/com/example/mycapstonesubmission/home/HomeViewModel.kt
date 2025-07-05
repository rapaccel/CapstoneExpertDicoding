package com.example.mycapstonesubmission.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mycapstonesubmission.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(moviesUseCase: MoviesUseCase) : ViewModel() {
    val movies = moviesUseCase.getAllMovies().asLiveData()
}