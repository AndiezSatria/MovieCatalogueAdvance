package com.andiez.moviecatalogueadvance.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "genres")
    var genres: String = "",
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String = "",
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "img")
    var img: String = "",
    @ColumnInfo(name = "backdrop")
    var backdrop: String = "",
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = "",
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double = 0.0,
    @ColumnInfo(name = "overview")
    var overview: String = "",
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String = "",
    @ColumnInfo(name = "runtime")
    var runtime: Int = 0,
    @ColumnInfo(name = "status")
    var status: String = "",
    @ColumnInfo(name = "tagline")
    var tagline: String = ""
)