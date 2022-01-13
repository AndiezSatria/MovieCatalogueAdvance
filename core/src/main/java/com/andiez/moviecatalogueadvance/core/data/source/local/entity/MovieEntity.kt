package com.andiez.moviecatalogueadvance.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String = "",
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "img")
    var img: String = "",
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = "",
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double = 0.0,
    @ColumnInfo(name = "category")
    var category: String = ShowCategory.NowPlaying.toString(),
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)