package com.srhan.footballleague.domain.repository

import com.srhan.footballleague.domain.model.CompetitionDetailsModel
import kotlinx.coroutines.flow.Flow

interface CacheCompetitionRepository {
    suspend fun getAllCompetitionsFromLocal(): Flow<List<CompetitionDetailsModel>>
    suspend fun cacheCompetition(competitionDetails: List<CompetitionDetailsModel>)
}