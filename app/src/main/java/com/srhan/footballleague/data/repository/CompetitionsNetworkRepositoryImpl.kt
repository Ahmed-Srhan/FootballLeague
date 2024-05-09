package com.srhan.footballleague.data.repository

import androidx.room.withTransaction
import com.srhan.footballleague.data.local.CompetitionDatabase
import com.srhan.footballleague.data.mapper.toDomain
import com.srhan.footballleague.data.mapper.toEntity
import com.srhan.footballleague.data.model.Resource
import com.srhan.footballleague.data.remote.CompetitionApiService
import com.srhan.footballleague.domain.model.CompetitionModel
import com.srhan.footballleague.domain.repository.CompetitionsNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompetitionsNetworkRepositoryImpl @Inject constructor(
    private val competitionService: CompetitionApiService,
    private val db: CompetitionDatabase
) : CompetitionsNetworkRepository {

    override suspend fun getCompetitionsFromNetwork(): Flow<Resource<CompetitionModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = competitionService.getAllCompetitionsFromNetwork()
            if (response.isSuccessful) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    val competition = body.toDomain()
                    emit(Resource.Success(competition))
                    db.withTransaction {
                        db.competitionDao().deleteAllCompetitions()
                        db.competitionDao().cacheCompetition(
                            competition.competitionModel.map {
                                it.toEntity()
                            }
                        )
                    }
                }
            } else {
                emit(Resource.Error(Exception(response.message())))
            }
        } catch (e: Exception) {
            emit(Resource.Error(Exception(e.message ?: "An error occurred")))
        }
    }
}