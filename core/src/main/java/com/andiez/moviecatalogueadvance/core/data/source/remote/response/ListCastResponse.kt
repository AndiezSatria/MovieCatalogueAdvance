package com.andiez.moviecatalogueadvance.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListCastResponse(
    @field:SerializedName("cast")
    var casts: List<CastResponse> = emptyList()
)
