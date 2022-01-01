package com.andiez.moviecatalogueadvance.core.data.source.local.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var originalTitle: String = "",
    var title: String = "",
    var img: String = "",
    var releaseDate: String = "",
    var voteAverage: Double = 0.0,
    var category: String = ShowCategory.NowPlaying.toString()
) : RealmObject()