package com.test.fdj.provider

import com.test.fdj.data.TeamElement

interface SportsDataProvider {
    suspend fun provideTeamsOfLeague(leagueName: String) : List<TeamElement>
    suspend fun provideLeagues(): List<String>
}