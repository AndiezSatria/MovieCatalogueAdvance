package com.andiez.moviecatalogueadvance.core.data.source.local

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.GenreEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieDetailEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.TvShowEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    fun getTvShows(): Flow<List<TvShowEntity>> = movieDao.getTvShows()
    fun getPopularMovies(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity> = movieDao.getMovieDetail(id)

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)
    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity) =
        movieDao.insertDetailMovie(movieDetail)
}