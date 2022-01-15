package com.andiez.moviecatalogueadvance.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(
    @field:SerializedName("id")
    var id: Int = 0,
    @field:SerializedName("genres")
    var genres: List<GenreResponse> = emptyList(),
    @field:SerializedName("original_name")
    var originalTitle: String = "",
    @field:SerializedName("name")
    var title: String = "",
    @field:SerializedName("poster_path")
    var img: String? = "",
    @field:SerializedName("backdrop_path")
    var backdrop: String? = "",
    @field:SerializedName("first_air_date")
    var releaseDate: String = "",
    @field:SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @field:SerializedName("overview")
    var overview: String? = "",
    @field:SerializedName("spoken_languages")
    var originalLanguage: List<SpokenLanguages> = emptyList(),
    @field:SerializedName("episode_run_time")
    var runtime: List<Int> = emptyList(),
    @field:SerializedName("status")
    var status: String = "",
    @field:SerializedName("tagline")
    var tagline: String = "",
    @field:SerializedName("number_of_episodes")
    var episodeNumber: Int = 0,
    @field:SerializedName("number_of_seasons")
    var seasonNumber: Int = 0,
)