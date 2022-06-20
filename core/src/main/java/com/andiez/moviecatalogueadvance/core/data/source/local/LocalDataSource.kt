package com.andiez.moviecatalogueadvance.core.data.source.local

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.*
import com.andiez.moviecatalogueadvance.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    fun getTvShows(): Flow<List<TvShowEntity>> = movieDao.getTvShows()
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity> = movieDao.getMovieDetail(id)
    fun getTvDetail(id: Int): Flow<TvShowDetailEntity> = movieDao.getTvDetail(id)
    fun getMoviesFavorite(): Flow<List<MovieEntity>> = movieDao.getMoviesFavorite()
    fun getTvShowsFavorite(): Flow<List<TvShowEntity>> = movieDao.getTvShowsFavorite()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)
    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity) =
        movieDao.insertDetailMovie(movieDetail)

    suspend fun insertDetailTv(tvShowDetailEntity: TvShowDetailEntity) =
        movieDao.insertDetailTv(tvShowDetailEntity)

    fun updateMovieDetailFavorite(id: Int, state: Boolean) =
        movieDao.updateMovieDetailFavorite(id, state)

    fun updateTvDetailFavorite(id: Int, state: Boolean) =
        movieDao.updateTvDetailFavorite(id, state)
}