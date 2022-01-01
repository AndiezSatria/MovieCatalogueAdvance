package com.andiez.moviecatalogueadvance.core.utils

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.ShowCategory
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.MovieResponse
import com.andiez.moviecatalogueadvance.core.domain.model.Movie

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

    fun mapMovieEntitiesToDomains(input: List<MovieEntity>): List<Movie> = input.map { entity ->
        Movie(
            entity.id,
            entity.originalTitle,
            entity.title,
            entity.img,
            entity.releaseDate,
            entity.voteAverage,
        )
    }
}