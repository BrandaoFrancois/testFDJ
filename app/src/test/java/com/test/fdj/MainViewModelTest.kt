package com.test.fdj

import com.test.fdj.data.TeamElement
import com.test.fdj.usecase.GetLeaguesFilteredByUseCase
import com.test.fdj.usecase.GetOddTeamListForLeagueSortedAnalphabeticalyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class MainViewModelTest {
    private val crashGetOddUseCase =
        object : GetOddTeamListForLeagueSortedAnalphabeticalyUseCase {
            override suspend fun execute(leagueName: String): List<TeamElement> = throw IOException("No no ! Not working today ! Come tomorrow !")
        }

    private val crashGetLeaguesUseCase =
        object : GetLeaguesFilteredByUseCase {
            override suspend fun execute(input: String): List<String> = throw IOException("No no ! Still not working today ! Come tomorrow !")
        }

    private val emptyGetOddUseCase =
        object : GetOddTeamListForLeagueSortedAnalphabeticalyUseCase {
            override suspend fun execute(leagueName: String): List<TeamElement> = listOf()
        }

    private val emptyGetLeaguesUseCase =
        object : GetLeaguesFilteredByUseCase {
            override suspend fun execute(input: String): List<String> = listOf()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUpdateLeaguesCatchErrorsWithNoChange() =
        runTest {
            val leagueFilter = ""
            val mainViewModel = MainViewModel(crashGetLeaguesUseCase, crashGetOddUseCase)
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)

            Dispatchers.setMain(testDispatcher)

            try {
                mainViewModel.updateLeagues(leagueFilter)
                Assert.assertEquals(0, mainViewModel.leagues.value.size)
            } finally {
                Dispatchers.resetMain()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSelectLeagueCatchErrorsWithNoChange() =
        runTest {
            val leagueToSelect = ""
            val mainViewModel = MainViewModel(crashGetLeaguesUseCase, crashGetOddUseCase)
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)

            Dispatchers.setMain(testDispatcher)

            try {
                mainViewModel.selectLeague(leagueToSelect)
                Assert.assertEquals(0, mainViewModel.teams.value.size)
            } finally {
                Dispatchers.resetMain()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSelectLeagueTeamsVisibilityGoTrue() =
        runTest {
            val leagueToSelect = ""
            val mainViewModel = MainViewModel(emptyGetLeaguesUseCase, emptyGetOddUseCase)
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)

            Dispatchers.setMain(testDispatcher)

            Assert.assertEquals(false, mainViewModel.isTeamsVisible.value)

            try {
                mainViewModel.selectLeague(leagueToSelect)
                Assert.assertEquals(true, mainViewModel.isTeamsVisible.value)
            } finally {
                Dispatchers.resetMain()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUpdateLeaguesTeamsVisibilityGoFalse() =
        runTest {
            val leagueToSelect = ""
            val mainViewModel = MainViewModel(emptyGetLeaguesUseCase, emptyGetOddUseCase)
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)

            Dispatchers.setMain(testDispatcher)

            try {
                mainViewModel.selectLeague(leagueToSelect)
                Assert.assertEquals(true, mainViewModel.isTeamsVisible.value)
                mainViewModel.updateLeagues(leagueToSelect)
                Assert.assertEquals(false, mainViewModel.isTeamsVisible.value)
            } finally {
                Dispatchers.resetMain()
            }
        }
}
