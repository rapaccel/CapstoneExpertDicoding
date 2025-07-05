package com.example.mycapstonesubmission.core.data

import com.example.mycapstonesubmission.core.data.source.local.LocalDataSource
import com.example.mycapstonesubmission.core.data.source.remote.RemoteDataSource
import com.example.mycapstonesubmission.core.data.source.remote.network.ApiResponse
import com.example.mycapstonesubmission.core.data.source.remote.response.ResultsItem
import com.example.mycapstonesubmission.core.domain.model.Movies
import com.example.mycapstonesubmission.core.domain.repository.IMoviesRepository
import com.example.mycapstonesubmission.core.utils.AppExecutors
import com.example.mycapstonesubmission.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Moviesrepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMoviesRepository {

    override fun getAllMovies(): Flow<UiState<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val moviesList = DataMapper.mapResponsetoEntities(data)
                localDataSource.insertMovies(moviesList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteMovies(
        movies: Movies,
        state: Boolean
    ) {
        val moviesEntity = DataMapper.mapDomainToEntities(movies)
        withContext(Dispatchers.IO) {
            localDataSource.setFavoriteMovies(moviesEntity, state)
        }
    }

}