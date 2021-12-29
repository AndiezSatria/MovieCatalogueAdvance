package com.andiez.moviecatalogueadvance.core.data.source.local.realm

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.GenreEntity
import io.realm.Realm
import io.realm.kotlin.toFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDao @Inject constructor(private val realm: Realm) : IMovieDao {
    override fun getGenres(): Flow<List<GenreEntity>> {
        return realm.where(GenreEntity::class.java).findAllAsync().toFlow()
    }
}