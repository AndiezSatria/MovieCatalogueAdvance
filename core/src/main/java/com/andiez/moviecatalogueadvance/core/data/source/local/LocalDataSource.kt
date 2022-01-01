package com.andiez.moviecatalogueadvance.core.data.source.local

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.GenreEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.realm.IMovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: IMovieDao) {
    fun getGenre(): Flow<List<GenreEntity>> = movieDao.getGenres()
    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    fun getPopularMovies(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()

    suspend fun insertGenres(genres: List<GenreEntity>) = movieDao.insertGenres(genres)
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)
}