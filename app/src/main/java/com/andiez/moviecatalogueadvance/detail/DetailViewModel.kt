package com.andiez.moviecatalogueadvance.detail

import android.util.Log
import androidx.lifecycle.*
import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.domain.model.Cast
import com.andiez.moviecatalogueadvance.core.domain.model.MovieDetail
import com.andiez.moviecatalogueadvance.core.domain.model.TvShowDetail
import com.andiez.moviecatalogueadvance.core.domain.usecase.MovieUseCase
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {
    private val id: MutableLiveData<Int> = MutableLiveData()
    private val _showType: MutableLiveData<ShowType> = MutableLiveData()
    val showType: LiveData<ShowType> get() = _showType

    fun setIdAndShowType(input: String) {
        val values = input.split("&")
        try {
            id.value = values[0].toInt()
            _showType.value = ShowType.valueOf(values[1])
        } catch (e: Exception) {
            Log.e("***", e.message.toString())
        }
    }

    val casts: LiveData<Resource<List<Cast>>> = Transformations.switchMap(id) { id ->
        Transformations.switchMap(_showType) { type ->
            useCase.getCasts(
                if (type == ShowType.TvShow) "tv" else "movie",
                id
            ).asLiveData()
        }
    }

    val movieDetail: LiveData<Resource<MovieDetail>> = Transformations.switchMap(id) { id ->
        useCase.getDetailMovie(id).asLiveData()
    }

    val tvShowDetail: LiveData<Resource<TvShowDetail>> = Transformations.switchMap(id) { id ->
        useCase.getDetailTvShow(id).asLiveData()
    }

    fun setMovieFavorite(id: Int, state: Boolean) {
        useCase.updateMovieFavorite(id, state)
    }

    fun setTvFavorite(id: Int, state: Boolean) {
        useCase.updateTvFavorite(id, state)
    }
}