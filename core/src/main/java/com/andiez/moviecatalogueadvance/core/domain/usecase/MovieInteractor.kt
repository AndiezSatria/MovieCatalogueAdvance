package com.andiez.moviecatalogueadvance.core.domain.usecase

import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.Cast
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.MovieDetail
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
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

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return repository.getPopularMovies()
    }

    override fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>> {
        return repository.getDetailMovie(id)
    }

    override fun getCasts(id: Int): Flow<Resource<List<Cast>>> {
        return repository.getCasts(id)
    }
}