package com.andiez.moviecatalogueadvance.core.utils

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.*
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.*
import com.andiez.moviecatalogueadvance.core.domain.model.*
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
            isFavorite = entity.isFavorite
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
            entity.isFavorite
        )
    }

    private fun mapGenresResponseToEntities(input: List<GenreResponse>): String {
        var genres = ""
        for (i in input.indices) {
            genres += when (i) {
                0 -> input[i].name + (if (input.size != 1) "," else "")
                input.size - 1 -> " ${input[i].name}"
                else -> " ${input[i].name},"
            }
        }
        return genres
    }

    private fun mapSpokenLanguageResponseToEntity(input: List<SpokenLanguages>): String {
        var languages = ""
        for (i in input.indices) {
            languages += when (i) {
                0 -> input[i].name + (if (input.size != 1) "," else "")
                input.size - 1 -> " ${input[i].name}"
                else -> " ${input[i].name},"
            }
        }
        return languages
    }

    private fun mapRuntimesResponseToEntity(input: List<Int>): String {
        var runtimes = ""
        for (i in input.indices) {
            runtimes += when (i) {
                0 -> "${input[i]}" + (if (input.size != 1) "," else "")
                input.size - 1 -> " ${input[i]}"
                else -> " ${input[i]},"
            }
        }
        return runtimes
    }

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
            mapSpokenLanguageResponseToEntity(input.originalLanguage),
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

    fun mapTvShowDetailResponseToEntity(input: TvShowDetailResponse): TvShowDetailEntity =
        TvShowDetailEntity(
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
            mapSpokenLanguageResponseToEntity(input.originalLanguage),
            mapRuntimesResponseToEntity(input.runtime),
            input.status,
            input.tagline
        )

    fun mapTvShowDetailEntityToDomain(input: TvShowDetailEntity?): TvShowDetail = TvShowDetail(
        input?.id ?: 0,
        input?.genres ?: "",
        input?.originalTitle ?: "",
        input?.title ?: "",
        input?.img ?: "",
        input?.backdrop ?: "",
        input?.releaseDate ?: "",
        input?.voteAverage ?: 0.0,
        input?.overview ?: "",
        input?.isFavorite ?: false,
        input?.originalLanguage ?: "",
        input?.runtimes ?: "",
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
        input.runtime.toString(),
        input.status,
        if (input.tagline != "") "\"${input.tagline}\"" else input.tagline
    )

    fun mapDetailTvShowDomainToPresenter(input: TvShowDetail): DetailItem = DetailItem(
        input.id,
        input.genres,
        input.originalTitle,
        input.title,
        input.img,
        input.backdrop,
        CommonUtils.convertFormatDate(input.releaseDate),
        input.voteAverage,
        input.overview,
        input.isFavorite,
        input.originalLanguage,
        input.runtimes,
        input.status,
        if (input.tagline != "") "\"${input.tagline}\"" else input.tagline
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