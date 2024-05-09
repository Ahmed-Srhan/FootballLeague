package com.srhan.footballleague.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competition")
data class CompetitionEntity(
    @PrimaryKey
    var id: Int?,
    var areaName: String? = null,
    var name: String? = null,
    var code: String? = null,
    var type: String? = null,
    var emblem: String? = null,
    var plan: String? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var numberOfAvailableSeasons: Int? = null,
    var lastUpdated: String? = null
)
