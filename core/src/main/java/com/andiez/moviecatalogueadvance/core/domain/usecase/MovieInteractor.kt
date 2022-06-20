package com.andiez.moviecatalogueadvance.core.domain.usecase

import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.*
import com.andiez.moviecatalogueadvance.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val repository: IMovieRepository) : MovieUseCase {
    override fun getMovies(): Flow<Resource<List<Movie>>> {
        return repository.getMovies()
    }

    override fun getTvShows(): Flow<Resource<List<TvShow>>> {
        return repository.getTvShows()
    }

    override fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>> {
        return repository.getDetailMovie(id)
    }

    override fun getDetailTvShow(id: Int): Flow<Resource<TvShowDetail>> {
        return repository.getDetailTvShow(id)
    }

    override fun getCasts(type: String, id: Int): Flow<Resource<List<Cast>>> {
        return repository.getCasts(type, id)
    }

    override fun updateMovieFavorite(id: Int, state: Boolean) {
        repository.setMovieFavorite(id, state)
    }

    override fun getMovieFavorite(): Flow<List<Movie>> {
        return repository.getMoviesFavorite()
    }

    override fun updateTvFavorite(id: Int, state: Boolean) {
        repository.setTvFavorite(id, state)
    }

    override fun getTvShowsFavorite(): Flow<List<TvShow>> {
        return repository.getTvShowsFavorite()
    }

    override fun searchMovie(query: String): Flow<Resource<List<Movie>>> {
        return repository.getSearchedMovies(query)
    }

    override fun searchTvShow(query: String): Flow<Resource<List<TvShow>>> {
        return repository.getSearchedTvShows(query)
    }
}