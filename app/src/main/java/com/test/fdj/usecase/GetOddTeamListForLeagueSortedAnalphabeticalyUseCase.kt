package com.test.fdj.usecase

import com.test.fdj.data.TeamElement

interface GetOddTeamListForLeagueSortedAnalphabeticalyUseCase {
    suspend fun execute(leagueName: String): List<TeamElement>
}