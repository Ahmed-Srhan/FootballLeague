package com.srhan.footballleague.domain.usecase

import com.srhan.footballleague.domain.repository.CompetitionsNetworkRepository
import javax.inject.Inject

class GetCompetitionFromNetworkUseCase @Inject constructor(
    private val competitionsNetworkRepository: CompetitionsNetworkRepository
) {
    suspend operator fun invoke() = competitionsNetworkRepository.getCompetitionsFromNetwork()
}