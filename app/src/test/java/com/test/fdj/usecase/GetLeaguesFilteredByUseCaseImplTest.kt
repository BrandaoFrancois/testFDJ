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

class GetLeaguesFilteredByUseCaseImplTest {
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
    fun testGettingLeaguesFilteredByWithCaseInsensitive() = runTest {
        val filterString = "eN"
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val testUseCase = GetLeaguesFilteredByUseCaseImpl(sportsDataProvider, testDispatcher)
        lateinit var resultToTestFormatted : String

        launch {
            val resultToTest = testUseCase.execute(filterString)
            resultToTestFormatted = resultToTest.joinToString(separator = "") { it }
        }
        advanceUntilIdle()

        Assert.assertEquals("englandEnglandENgland", resultToTestFormatted)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGettingLeaguesFilteredByWithNormalString() = runTest {
        val filterString = "alpha"
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val testUseCase = GetLeaguesFilteredByUseCaseImpl(sportsDataProvider, testDispatcher)
        lateinit var resultToTestFormatted : String

        launch {
            val resultToTest = testUseCase.execute(filterString)
            resultToTestFormatted = resultToTest.joinToString(separator = "") { it }
        }
        advanceUntilIdle()

        Assert.assertEquals("alphaalphabet", resultToTestFormatted)
    }
}