package com.andiez.moviecatalogueadvance.core.domain.repository

import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getTvShows(): Flow<Resource<List<TvShow>>>
    fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>>
    fun getDetailTvShow(id: Int): Flow<Resource<TvShowDetail>>
    fun getCasts(type: String, id: Int): Flow<Resource<List<Cast>>>
    fun setMovieFavorite(id: Int, state: Boolean)
    fun getMoviesFavorite(): Flow<List<Movie>>

    fun setTvFavorite(id: Int, state: Boolean)
    fun getTvShowsFavorite(): Flow<List<TvShow>>
}