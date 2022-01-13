package com.andiez.moviecatalogueadvance.core.domain.repository

import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.Cast
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.MovieDetail
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getTvShows(): Flow<Resource<List<TvShow>>>
    fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>>
    fun getCasts(id: Int): Flow<Resource<List<Cast>>>
}