package com.andiez.moviecatalogueadvance.core.data

import com.andiez.moviecatalogueadvance.core.data.source.local.LocalDataSource
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.ShowCategory
import com.andiez.moviecatalogueadvance.core.data.source.remote.RemoteDataSource
import com.andiez.moviecatalogueadvance.core.data.source.remote.network.ApiResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.MovieResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.TvShowResponse
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import com.andiez.moviecatalogueadvance.core.domain.repository.IMovieRepository
import com.andiez.moviecatalogueadvance.core.utils.AppExecutors
import com.andiez.moviecatalogueadvance.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { DataMapper.mapMovieEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                // data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                localDataSource.insertMovies(DataMapper.mapMovieResponsesToEntities(data))
            }

        }.asFlow()

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopularMovies()
                    .map { DataMapper.mapMovieEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                // data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                localDataSource.insertMovies(
                    DataMapper.mapMovieResponsesToEntities(
                        data,
                        ShowCategory.Popular
                    )
                )
            }

        }.asFlow()

    override fun getTvShows(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getTvShows()
                    .map { DataMapper.mapTvEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                // data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                localDataSource.insertTvShows(
                    DataMapper.mapTvResponsesToEntities(data)
                )
            }

        }.asFlow()
}