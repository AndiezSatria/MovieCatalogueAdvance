package com.andiez.moviecatalogueadvance.core.domain.model

data class MovieDetail(
    var id: Int = 0,
    var genres: String = "",
    var originalTitle: String = "",
    var title: String = "",
    var img: String = "",
    var backdrop: String = "",
    var releaseDate: String = "",
    var voteAverage: Double = 0.0,
    var overview: String? = "",
    var isFavorite: Boolean = false,
    var originalLanguage: String = "",
    var runtime: Int? = 0,
    var status: String = "",
    var tagline: String = ""
)
