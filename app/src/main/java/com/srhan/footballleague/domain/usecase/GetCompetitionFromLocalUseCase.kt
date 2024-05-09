package com.srhan.footballleague.domain.usecase

import com.srhan.footballleague.domain.repository.CacheCompetitionRepository
import javax.inject.Inject

class GetCompetitionFromLocalUseCase @Inject constructor(
    private val cacheCompetitionRepository: CacheCompetitionRepository
) {
    suspend operator fun invoke() = cacheCompetitionRepository.getAllCompetitionsFromLocal()
}