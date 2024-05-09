package com.srhan.footballleague.domain.usecase

import com.srhan.footballleague.domain.model.CompetitionDetailsModel
import com.srhan.footballleague.domain.repository.CacheCompetitionRepository
import javax.inject.Inject

class CacheCompetitionUseCade @Inject constructor(
    private val cacheCompetitionRepository: CacheCompetitionRepository
) {
    //cacheCompetition
    suspend operator fun invoke(competitionDetails: List<CompetitionDetailsModel>) =
        cacheCompetitionRepository.cacheCompetition(competitionDetails)
}