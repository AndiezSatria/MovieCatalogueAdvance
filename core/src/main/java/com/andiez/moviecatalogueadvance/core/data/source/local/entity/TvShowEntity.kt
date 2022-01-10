package com.andiez.moviecatalogueadvance.core.data.source.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class TvShowEntity(
    @PrimaryKey
    var id: Int = 0,
    var originalTitle: String = "",
    var title: String = "",
    var img: String = "",
    var firstAired: String = "",
    var voteAverage: Double = 0.0,
    var category: String = ShowCategory.NowPlaying.toString(),
) : RealmObject()