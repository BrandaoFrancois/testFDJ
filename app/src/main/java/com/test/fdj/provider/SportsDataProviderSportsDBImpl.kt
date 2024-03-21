package com.test.fdj.provider

import com.test.fdj.data.TeamElement
import com.test.fdj.hilt.DefaultDispatcher
import com.test.fdj.module.sportsdb.SportsDBService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SportsDataProviderSportsDBImpl @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : SportsDataProvider {
    private val sportsDBService: SportsDBService by lazy {
        SportsDBService.create()
    }

    override suspend fun provideTeamsOfLeague(leagueName: String): List<TeamElement> = withContext(defaultDispatcher) {
        return@withContext sportsDBService.searchTeams(leagueName).teams.map {searchTeamResultItem ->
            TeamElement(
                name = searchTeamResultItem.name,
                pictureURL = searchTeamResultItem.teamBadge
            )
        }
    }

    override suspend fun provideLeagues(): List<String> = withContext(defaultDispatcher) {
        return@withContext sportsDBService.getListOfAllLeagues().leagues.map {leagueResultItem ->
            leagueResultItem.leagueName
        }
    }
}