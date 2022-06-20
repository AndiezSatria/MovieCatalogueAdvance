package com.andiez.moviecatalogueadvance.core.domain.model

data class TvShow(
    var id: Int = 0,
    var originalTitle: String = "",
    var title: String = "",
    var img: String = "",
    var firstAired: String = "",
    var voteAverage: Double = 0.0,
    var isFavorite: Boolean = false
)