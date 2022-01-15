package com.andiez.moviecatalogueadvance.core.domain.model

import java.util.*

data class TvShow(
    var id: Int = 0,
    var originalTitle: String = "",
    var title: String = "",
    var img: String = "",
    var firstAired: Date? = null,
    var voteAverage: Double = 0.0,
    var isFavorite: Boolean = false
)