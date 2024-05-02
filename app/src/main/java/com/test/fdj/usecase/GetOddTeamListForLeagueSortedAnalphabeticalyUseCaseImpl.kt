package com.test.fdj.usecase

import com.test.fdj.data.TeamElement
import com.test.fdj.hilt.DefaultDispatcher
import com.test.fdj.provider.SportsDataProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl @Inject constructor (
    private val sportsDataProvider: SportsDataProvider,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : GetOddTeamListForLeagueSortedAnalphabeticalyUseCase{
    override suspend fun execute(leagueName: String): List<TeamElement> = withContext(defaultDispatcher) {
        return@withContext sportsDataProvider.provideTeamsOfLeague(leagueName)
            .filterIndexed { index, _ -> index % 2 == 0 }
            .sortedByDescending { it.name }
    }
}