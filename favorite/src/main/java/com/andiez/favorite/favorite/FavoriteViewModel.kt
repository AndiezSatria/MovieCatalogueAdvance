package com.andiez.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import com.andiez.moviecatalogueadvance.core.domain.usecase.MovieUseCase

class FavoriteViewModel(useCase: MovieUseCase) : ViewModel() {
    val movies: LiveData<List<Movie>> = useCase.getMovieFavorite().asLiveData()
    val tvShows: LiveData<List<TvShow>> = useCase.getTvShowsFavorite().asLiveData()
}