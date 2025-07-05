package com.example.mycapstonesubmission.di

import com.example.mycapstonesubmission.core.domain.usecase.MoviesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun provideMoviesUseCase(): MoviesUseCase
}