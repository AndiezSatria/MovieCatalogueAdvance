package com.andiez.moviecatalogueadvance.core.domain.usecase

import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getTvShows(): Flow<Resource<List<TvShow>>>
    fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>>
    fun getDetailTvShow(id: Int): Flow<Resource<TvShowDetail>>
    fun getCasts(type: String, id: Int): Flow<Resource<List<Cast>>>

    fun updateMovieFavorite(id: Int, state: Boolean)
    fun getMovieFavorite(): Flow<List<Movie>>
    fun updateTvFavorite(id: Int, state: Boolean)
    fun getTvShowsFavorite(): Flow<List<TvShow>>

    fun searchMovie(query: String): Flow<Resource<List<Movie>>>
    fun searchTvShow(query: String): Flow<Resource<List<TvShow>>>
}