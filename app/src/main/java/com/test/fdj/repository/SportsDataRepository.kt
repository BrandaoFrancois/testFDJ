package com.test.fdj.repository

import com.test.fdj.data.TeamElement

interface SportsDataRepository {
    suspend fun getTeamsOfLeague(leagueName: String): List<TeamElement>

    suspend fun getLeagues(): List<String>
}
