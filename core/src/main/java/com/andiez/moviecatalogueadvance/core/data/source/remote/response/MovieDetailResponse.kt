package com.andiez.moviecatalogueadvance.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @field:SerializedName("id")
    var id: Int = 0,
    @field:SerializedName("genres")
    var genres: List<GenreResponse> = emptyList(),
    @field:SerializedName("original_title")
    var originalTitle: String = "",
    @field:SerializedName("title")
    var title: String = "",
    @field:SerializedName("poster_path")
    var img: String? = "",
    @field:SerializedName("backdrop_path")
    var backdrop: String? = "",
    @field:SerializedName("release_date")
    var releaseDate: String = "",
    @field:SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @field:SerializedName("overview")
    var overview: String? = "",
    @field:SerializedName("spoken_languages")
    var originalLanguage: List<SpokenLanguages> = emptyList(),
    @field:SerializedName("runtime")
    var runtime: Int? = 0,
    @field:SerializedName("status")
    var status: String = "",
    @field:SerializedName("tagline")
    var tagline: String = ""
)