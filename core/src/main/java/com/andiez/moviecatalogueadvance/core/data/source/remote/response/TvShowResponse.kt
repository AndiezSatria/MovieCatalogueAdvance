package com.andiez.moviecatalogueadvance.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("original_name")
    var originalTitle: String,
    @field:SerializedName("name")
    var title: String,
    @field:SerializedName("poster_path")
    var img: String?,
    @field:SerializedName("first_air_date")
    var firstAired: String? = "",
    @field:SerializedName("vote_average")
    var voteAverage: Double
)