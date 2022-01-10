package com.andiez.moviecatalogueadvance.core.utils

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.ShowCategory
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.TvShowEntity
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.MovieResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.TvShowResponse
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowItem
import java.text.SimpleDateFormat
import java.util.*

object DataMapper {
    fun mapMovieResponsesToEntities(
        input: List<MovieResponse>,
        showCategory: ShowCategory = ShowCategory.NowPlaying
    ): List<MovieEntity> = input.map { response ->
        MovieEntity(
            response.id,
            response.originalTitle,
            response.title,
            response.img,
            response.releaseDate,
            response.voteAverage,
            showCategory.toString()
        )
    }

    fun mapTvResponsesToEntities(
        input: List<TvShowResponse>,
        showCategory: ShowCategory = ShowCategory.NowPlaying
    ): List<TvShowEntity> = input.map { response ->
        TvShowEntity(
            response.id,
            response.originalTitle,
            response.title,
            response.img ?: "",
            response.firstAired,
            response.voteAverage,
            showCategory.toString()
        )
    }

    fun mapMovieDomainsToPresenters(input: List<Movie>?): List<ShowItem> = input?.map { movie ->
        ShowItem(
            movie.id,
            movie.title,
            movie.img,
            movie.voteAverage,
        )
    } ?: emptyList()

    fun mapMovieEntitiesToDomains(input: List<MovieEntity>): List<Movie> = input.map { entity ->
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        Movie(
            entity.id,
            entity.originalTitle,
            entity.title,
            entity.img,
            formatter.parse(entity.releaseDate),
            entity.voteAverage,
        )
    }

    fun mapTvDomainsToPresenters(input: List<TvShow>?): List<ShowItem> = input?.map { tv ->
        ShowItem(
            tv.id,
            tv.title,
            tv.img,
            tv.voteAverage,
        )
    } ?: emptyList()

    fun mapTvEntitiesToDomains(input: List<TvShowEntity>): List<TvShow> = input.map { entity ->
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        TvShow(
            entity.id,
            entity.originalTitle,
            entity.title,
            entity.img,
            formatter.parse(entity.firstAired),
            entity.voteAverage,
        )
    }
}