package com.example.mycapstonesubmission.core.data.source.remote

import android.util.Log
import com.example.mycapstonesubmission.core.data.source.remote.network.ApiResponse
import com.example.mycapstonesubmission.core.data.source.remote.network.ApiService
import com.example.mycapstonesubmission.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray != null && dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results.filterNotNull()))
                    } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}