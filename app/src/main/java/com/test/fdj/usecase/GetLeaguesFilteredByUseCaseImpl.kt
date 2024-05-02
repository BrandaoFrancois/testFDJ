package com.test.fdj.usecase

import com.test.fdj.hilt.DefaultDispatcher
import com.test.fdj.provider.SportsDataProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLeaguesFilteredByUseCaseImpl @Inject constructor (
    private val sportsDataProvider: SportsDataProvider,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : GetLeaguesFilteredByUseCase {
    override suspend fun execute(input: String): List<String> = withContext(defaultDispatcher) {
        return@withContext sportsDataProvider.provideLeagues()
            .filter { leagueName -> leagueName.lowercase().startsWith(input.lowercase()) }
    }
}