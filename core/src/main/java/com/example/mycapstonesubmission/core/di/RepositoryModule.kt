package com.example.mycapstonesubmission.core.di

import com.example.mycapstonesubmission.core.data.Moviesrepository
import com.example.mycapstonesubmission.core.data.source.local.LocalDataSource
import com.example.mycapstonesubmission.core.data.source.remote.RemoteDataSource
import com.example.mycapstonesubmission.core.domain.repository.IMoviesRepository
import com.example.mycapstonesubmission.core.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        appExecutors: AppExecutors
    ) : IMoviesRepository = Moviesrepository(remoteDataSource,localDataSource)

}