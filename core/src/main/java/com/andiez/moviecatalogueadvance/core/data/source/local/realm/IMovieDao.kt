package com.andiez.moviecatalogueadvance.core.data.source.local.realm

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.GenreEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieDao {
    fun getGenres(): Flow<List<GenreEntity>>
    suspend fun insertGenres(genres: List<GenreEntity>)

    fun getMovies(): Flow<List<MovieEntity>>
    suspend fun insertMovies(movies: List<MovieEntity>)

    fun getPopularMovies(): Flow<List<MovieEntity>>
}