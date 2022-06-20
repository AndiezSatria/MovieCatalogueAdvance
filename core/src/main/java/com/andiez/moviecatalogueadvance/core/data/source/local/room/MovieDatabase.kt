package com.andiez.moviecatalogueadvance.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieDetailEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.TvShowDetailEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.TvShowEntity

@Database(
    entities = [
        TvShowDetailEntity::class,
        MovieDetailEntity::class,
        TvShowEntity::class,
        MovieEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}