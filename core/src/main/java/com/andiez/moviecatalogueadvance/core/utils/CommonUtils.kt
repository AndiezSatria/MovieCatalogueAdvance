package com.andiez.moviecatalogueadvance.core.utils

import com.andiez.moviecatalogueadvance.core.presenter.model.ShowType
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    fun createDeeplinkArgs(id: Int, showType: ShowType): String {
        return "$id&${showType}"
    }

    fun convertFormatDate(oldDate: String): String {
        val oldFormat = "yyyy-MM-dd"
        val newFormat = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(oldFormat, Locale.getDefault())
        val date = sdf.parse(oldDate)
        sdf.applyPattern(newFormat)
        return sdf.format(date!!)
    }
}