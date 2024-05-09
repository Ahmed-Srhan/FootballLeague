package com.srhan.footballleague.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompetitionDetailsModel(
    var id: Int? = null,
    var areaName: String? = null,
    var name: String? = null,
    var code: String? = null,
    var type: String? = null,
    var emblem: String? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var numberOfAvailableSeasons: Int? = null,
    var lastUpdated: String? = null
) : Parcelable
