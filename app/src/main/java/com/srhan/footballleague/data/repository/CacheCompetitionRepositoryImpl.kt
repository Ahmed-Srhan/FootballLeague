package com.srhan.footballleague.data.repository

import androidx.room.withTransaction
import com.srhan.footballleague.data.local.CompetitionDatabase
import com.srhan.footballleague.data.mapper.toDomain
import com.srhan.footballleague.data.mapper.toEntity
import com.srhan.footballleague.domain.model.CompetitionDetailsModel
import com.srhan.footballleague.domain.repository.CacheCompetitionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheCompetitionRepositoryImpl @Inject constructor(
    private val db: CompetitionDatabase
) : CacheCompetitionRepository {

    override suspend fun getAllCompetitionsFromLocal(): Flow<List<CompetitionDetailsModel>> {
        return db.competitionDao().getAllCompetitionsFromLocal().map {
            it.map { competitionEntity ->
                competitionEntity.toDomain()
            }
        }
    }

    override suspend fun cacheCompetition(competitionDetails: List<CompetitionDetailsModel>) {
        db.withTransaction {
            db.competitionDao().deleteAllCompetitions()
            db.competitionDao().cacheCompetition(
                competitionDetails.map {
                    it.toEntity()
                }
            )
        }

    }
}
