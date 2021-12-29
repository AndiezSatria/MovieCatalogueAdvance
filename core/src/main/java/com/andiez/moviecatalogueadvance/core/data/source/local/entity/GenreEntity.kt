package com.andiez.moviecatalogueadvance.core.data.source.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GenreEntity(
    @PrimaryKey
    var id: Int,
    var name: String
) : RealmObject()