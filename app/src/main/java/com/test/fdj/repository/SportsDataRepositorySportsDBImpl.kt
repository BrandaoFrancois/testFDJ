package com.test.fdj.repository

import com.test.fdj.data.TeamElement
import com.test.fdj.di.DefaultDispatcher
import com.test.fdj.module.sportsdb.SportsDBService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SportsDataRepositorySportsDBImpl @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : SportsDataRepository {
    private val sportsDBService: SportsDBService by lazy {
        SportsDBService.create()
    }

    override suspend fun getTeamsOfLeague(leagueName: String): List<TeamElement> = withContext(defaultDispatcher) {
        return@withContext sportsDBService.searchTeams(leagueName).teams.map {searchTeamResultItem ->
            TeamElement(
                name = searchTeamResultItem.name,
                pictureURL = searchTeamResultItem.teamBadge
            )
        }
    }

    override suspend fun getLeagues(): List<String> = withContext(defaultDispatcher) {
        return@withContext sportsDBService.getListOfAllLeagues().leagues.map {leagueResultItem ->
            leagueResultItem.leagueName
        }
    }
}