package com.example.mycapstonesubmission.di

import com.example.mycapstonesubmission.core.domain.usecase.MoviesInteractor
import com.example.mycapstonesubmission.core.domain.usecase.MoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMoviesUseCase(moviesInteractor: MoviesInteractor): MoviesUseCase


}