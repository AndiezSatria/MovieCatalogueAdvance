package com.andiez.moviecatalogueadvance.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import com.andiez.moviecatalogueadvance.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<Movie>>> = useCase.getMovies().asLiveData()
    fun getTvShows(): LiveData<Resource<List<TvShow>>> = useCase.getTvShows().asLiveData()
}