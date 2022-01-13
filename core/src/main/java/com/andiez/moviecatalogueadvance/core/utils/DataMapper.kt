package com.andiez.moviecatalogueadvance.core.utils

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieDetailEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.ShowCategory
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.TvShowEntity
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.*
import com.andiez.moviecatalogueadvance.core.domain.model.Cast
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.domain.model.MovieDetail
import com.andiez.moviecatalogueadvance.core.domain.model.TvShow
import com.andiez.moviecatalogueadvance.core.presenter.model.CastItem
import com.andiez.moviecatalogueadvance.core.presenter.model.DetailItem
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

    private fun mapGenresResponseToEntities(input: List<GenreResponse>): String {
        var genres = ""
        for (i in input.indices) {
            genres += when (i) {
                0 -> "${input[i].name},"
                input.size - 1 -> " ${input[i].name}"
                else -> " ${input[i].name},"
            }
        }
        return genres
    }

    private fun mapSpokenLanguageResponseToEntity(input: SpokenLanguages): String = input.name

    fun mapMovieDetailResponseToEntity(input: MovieDetailResponse): MovieDetailEntity =
        MovieDetailEntity(
            input.id,
            mapGenresResponseToEntities(input.genres),
            input.originalTitle,
            input.title,
            input.img ?: "",
            input.backdrop ?: "",
            input.releaseDate,
            input.voteAverage,
            input.overview ?: "",
            false,
            mapSpokenLanguageResponseToEntity(input.originalLanguage[0]),
            input.runtime ?: 0,
            input.status,
            input.tagline
        )

    fun mapMovieDetailEntityToDomain(input: MovieDetailEntity?): MovieDetail = MovieDetail(
        input?.id ?: 0,
        input?.genres ?: "",
        input?.originalTitle ?: "",
        input?.title ?: "",
        input?.img ?: "",
        input?.backdrop ?: "",
        input?.releaseDate ?: "",
        input?.voteAverage ?: 0.0,
        input?.overview,
        input?.isFavorite ?: false,
        input?.originalLanguage ?: "",
        input?.runtime,
        input?.status ?: "",
        input?.tagline ?: ""
    )

    fun mapCastDomainsToPresenters(input: List<Cast>?): List<CastItem> = input?.map {
        CastItem(it.id, it.character, it.name, it.img ?: "")
    } ?: emptyList()

    fun mapDetailMovieDomainToPresenter(input: MovieDetail): DetailItem = DetailItem(
        input.id,
        input.genres,
        input.originalTitle,
        input.title,
        input.img,
        input.backdrop,
        CommonUtils.convertFormatDate(input.releaseDate),
        input.voteAverage,
        input.overview ?: "Tidak ada Overview",
        input.isFavorite,
        input.originalLanguage,
        input.runtime ?: 0,
        input.status,
        "\"${input.tagline}\""
    )

    fun mapCastResponsesToDomains(input: List<CastResponse>): List<Cast> = input.map { response ->
        Cast(
            response.id,
            response.character,
            response.name,
            response.originalName,
            response.img
        )
    }
}