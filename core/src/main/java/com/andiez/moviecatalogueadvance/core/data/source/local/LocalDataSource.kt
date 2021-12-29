package com.andiez.moviecatalogueadvance.core.data.source.local

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.GenreEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.realm.IMovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: IMovieDao) {
    fun getGenre(): Flow<List<GenreEntity>> = movieDao.getGenres()
}