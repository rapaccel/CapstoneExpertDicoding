package com.example.mycapstonesubmission.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycapstonesubmission.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val getAllFavoriteUseCase: MoviesUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(getAllFavoriteUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
}