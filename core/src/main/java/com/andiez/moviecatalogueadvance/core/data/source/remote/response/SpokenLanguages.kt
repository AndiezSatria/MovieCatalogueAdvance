package com.andiez.moviecatalogueadvance.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguages(
    @field:SerializedName("name")
    var name: String = ""
)
