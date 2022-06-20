package com.andiez.moviecatalogueadvance.core.utils

import com.andiez.moviecatalogueadvance.core.presenter.model.ShowType
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    fun createDeeplinkArgs(id: Int, showType: ShowType, isSearch: Boolean = false): String {
        return "$id&${showType}&${if (isSearch) 1 else 0}"
    }

    fun convertFormatDate(oldDate: String): String {
        if (oldDate == "") return "No Date"
        val oldFormat = "yyyy-MM-dd"
        val newFormat = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(oldFormat, Locale.getDefault())
        val date = sdf.parse(oldDate)
        sdf.applyPattern(newFormat)
        return sdf.format(date!!)
    }
}