package com.andiez.moviecatalogueadvance.core.domain.usecase

import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getTvShows(): Flow<Resource<List<TvShow>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}