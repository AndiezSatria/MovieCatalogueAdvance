package com.andiez.moviecatalogueadvance.core.domain.repository

import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}