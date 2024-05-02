package com.test.fdj.usecase

import com.test.fdj.data.TeamElement
import com.test.fdj.provider.SportsDataProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImplTest {
    private val sportsDataProvider = object: SportsDataProvider {
        override suspend fun provideTeamsOfLeague(leagueName: String): List<TeamElement> {
            return listOf(
                TeamElement("A", ""),
                TeamElement("B", ""),
                TeamElement("C", ""),
                TeamElement("D", ""),
                TeamElement("E", ""),
                TeamElement("F", ""),
                TeamElement("G", "")
            )
        }

        override suspend fun provideLeagues(): List<String> {
            return listOf(
                "a",
                "alpa",
                "alpha",
                "alphabet",
                "b",
                "ba",
                "england",
                "England",
                "ENgland"
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGettingOddTeamListForLeagueSortedAnalphabeticaly() = runTest {
        val leagueNameToTest = ""
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val testUseCase = GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl(sportsDataProvider, testDispatcher)
        lateinit var resultToTestFormatted : String

        launch {
            val resultToTest = testUseCase.execute(leagueNameToTest)
            resultToTestFormatted = resultToTest.joinToString(separator = "") { it.name }
        }
        advanceUntilIdle()

        Assert.assertEquals("GECA", resultToTestFormatted)
    }
}