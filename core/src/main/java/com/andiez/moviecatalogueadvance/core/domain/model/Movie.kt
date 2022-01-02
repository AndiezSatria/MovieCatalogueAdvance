package com.andiez.moviecatalogueadvance.core.domain.model

import java.util.*

data class Movie(
    var id: Int = 0,
    var originalTitle: String = "",
    var title: String = "",
    var img: String = "",
    var releaseDate: Date? = null,
    var voteAverage: Double = 0.0,
)