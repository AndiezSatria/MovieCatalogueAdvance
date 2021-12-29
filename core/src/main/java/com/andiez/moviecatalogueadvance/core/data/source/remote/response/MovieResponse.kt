package com.andiez.moviecatalogueadvance.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("original_title")
    var originalTitle: String,
    @field:SerializedName("title")
    var title: String,
    @field:SerializedName("poster_path")
    var img: String,
    @field:SerializedName("release_date")
    var releaseDate: String,
    @field:SerializedName("vote_average")
    var voteAverage: Double,
    @field:SerializedName("genre_ids")
    var genreIds: List<Int>,
)