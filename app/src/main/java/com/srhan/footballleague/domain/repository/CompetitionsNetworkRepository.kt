package com.srhan.footballleague.domain.repository

import com.srhan.footballleague.data.model.Resource
import com.srhan.footballleague.domain.model.CompetitionModel
import kotlinx.coroutines.flow.Flow

interface CompetitionsNetworkRepository {
    suspend fun getCompetitionsFromNetwork(): Flow<Resource<CompetitionModel>>
}