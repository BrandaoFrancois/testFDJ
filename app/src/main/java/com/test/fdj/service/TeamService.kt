package com.test.fdj.service

import com.test.fdj.data.TeamElement

interface TeamService {
    suspend fun getOddTeamListForLeagueSortedAnalphabeticaly(leagueName: String): List<TeamElement>
    suspend fun getLeaguesFilteredBy(input: String): List<String>
}