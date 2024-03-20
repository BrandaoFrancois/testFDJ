package com.test.fdj.service

import com.test.fdj.data.TeamElement
import com.test.fdj.hilt.DefaultDispatcher
import com.test.fdj.provider.SportsDataProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamServiceImpl @Inject constructor (
    private val sportsDataProvider: SportsDataProvider,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : TeamService {
    override suspend fun getOddTeamListForLeagueSortedAnalphabeticaly(leagueName: String): List<TeamElement> = withContext(defaultDispatcher) {
        return@withContext sportsDataProvider.provideTeamsOfLeague(leagueName)
            .filterIndexed { index, _ -> index % 2 == 0 }
            .sortedByDescending { it.name }
    }

    override suspend fun getLeaguesFilteredBy(input: String): List<String> = withContext(defaultDispatcher) {
        return@withContext sportsDataProvider.provideLeagues()
            .filter { leagueName -> leagueName.lowercase().startsWith(input.lowercase()) }
    }
}

