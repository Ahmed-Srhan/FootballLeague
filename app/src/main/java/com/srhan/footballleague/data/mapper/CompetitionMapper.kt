package com.srhan.footballleague.data.mapper

import com.srhan.footballleague.data.model.response.CompetitionsJson
import com.srhan.footballleague.domain.model.CompetitionDetailsModel
import com.srhan.footballleague.domain.model.CompetitionModel


fun CompetitionsJson.toDomain() = CompetitionModel(
    competitionModel = competitions.map {
        CompetitionDetailsModel(
            areaName = it.area.name,
            name = it.name,
            code = it.code,
            type = it.plan,
            emblem = it.emblem,
            startDate = it.currentSeason.startDate,
            endDate = it.currentSeason.endDate,
            numberOfAvailableSeasons = it.numberOfAvailableSeasons,
            lastUpdated = it.lastUpdated
        )
    }
)





