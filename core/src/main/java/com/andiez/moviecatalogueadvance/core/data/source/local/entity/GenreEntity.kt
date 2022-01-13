package com.andiez.moviecatalogueadvance.core.data.source.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class GenreEntity(
    var id: Int = 0,
    var name: String = ""
)