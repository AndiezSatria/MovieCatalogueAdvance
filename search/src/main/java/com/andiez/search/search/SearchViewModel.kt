package com.andiez.search.search

import androidx.lifecycle.*
import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import com.andiez.moviecatalogueadvance.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val useCase: MovieUseCase) : ViewModel() {
    private val query = MutableSharedFlow<String>()
    fun setQuery(query: String) {
        viewModelScope.launch {
            this@SearchViewModel.query.emit(query)
        }
    }

    val searchMovieResult: LiveData<Resource<List<Movie>>> = query
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .flatMapLatest {
            useCase.searchMovie(it)
        }.asLiveData()

    val searchTvShowResult: LiveData<Resource<List<TvShow>>> = query
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .flatMapLatest {
            useCase.searchTvShow(it)
        }.asLiveData()
}