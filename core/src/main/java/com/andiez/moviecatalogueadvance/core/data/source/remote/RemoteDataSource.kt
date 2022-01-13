package com.andiez.moviecatalogueadvance.core.data.source.remote

import android.util.Log
import com.andiez.moviecatalogueadvance.core.data.source.remote.network.ApiResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.network.ApiService
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.CastResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.MovieDetailResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.MovieResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.getMovie()
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.getPopularMovie()
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getTvShows(): Flow<ApiResponse<List<TvShowResponse>>> = flow {
        try {
            val response = apiService.getTvShow()
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieDetailResponse>> = flow {
        try {
            val response = apiService.getDetailMovie(id)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getCasts(id: Int): Flow<ApiResponse<List<CastResponse>>> = flow {
        try {
            val response = apiService.getCastMovie(id)
            val dataArray = response.casts
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.casts))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)
}