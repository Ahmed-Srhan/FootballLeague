package com.srhan.footballleague.data.mapper

import com.srhan.footballleague.data.local.entities.CompetitionEntity
import com.srhan.footballleague.domain.model.CompetitionDetailsModel


fun CompetitionDetailsModel.toEntity() = CompetitionEntity(
    id = id,
    areaName = areaName,
    name = name,
    code = code,
    type = type,
    emblem = emblem,
    startDate = startDate,
    endDate = endDate,
    numberOfAvailableSeasons = numberOfAvailableSeasons,
    lastUpdated = lastUpdated
)

fun CompetitionEntity.toDomain() = CompetitionDetailsModel(
    id = id,
    areaName = areaName,
    name = name,
    code = code,
    type = type,
    emblem = emblem,
    startDate = startDate,
    endDate = endDate,
    numberOfAvailableSeasons = numberOfAvailableSeasons,
    lastUpdated = lastUpdated
)