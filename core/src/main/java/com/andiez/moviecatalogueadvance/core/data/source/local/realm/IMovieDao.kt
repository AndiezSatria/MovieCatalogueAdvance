package com.andiez.moviecatalogueadvance.core.data.source.local.realm

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

interface IMovieDao {
    fun getGenres(): Flow<List<GenreEntity>>
}