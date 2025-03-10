package com.test.fdj.usecase

import com.test.fdj.data.TeamElement
import com.test.fdj.di.DefaultDispatcher
import com.test.fdj.repository.SportsDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl @Inject constructor (
    private val sportsDataRepository: SportsDataRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : GetOddTeamListForLeagueSortedAnalphabeticalyUseCase{
    override suspend fun execute(leagueName: String): List<TeamElement> = withContext(defaultDispatcher) {
        return@withContext sportsDataRepository.getTeamsOfLeague(leagueName)
            .filterIndexed { index, _ -> index % 2 == 0 }
            .sortedByDescending { it.name }
    }
}
