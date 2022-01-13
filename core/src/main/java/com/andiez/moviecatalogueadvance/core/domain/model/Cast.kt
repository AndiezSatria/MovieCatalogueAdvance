package com.andiez.moviecatalogueadvance.core.domain.model

data class Cast(
    var id: Int = 0,
    var character: String = "",
    var name: String = "",
    var originalName: String = "",
    var img: String? = ""
)
