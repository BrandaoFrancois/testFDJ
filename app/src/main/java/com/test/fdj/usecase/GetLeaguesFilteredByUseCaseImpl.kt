package com.test.fdj.usecase

import com.test.fdj.di.DefaultDispatcher
import com.test.fdj.repository.SportsDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLeaguesFilteredByUseCaseImpl
    @Inject
    constructor(
        private val sportsDataRepository: SportsDataRepository,
        @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    ) : GetLeaguesFilteredByUseCase {
        override suspend fun execute(input: String): List<String> =
            withContext(defaultDispatcher) {
                return@withContext sportsDataRepository
                    .getLeagues()
                    .filter { leagueName -> leagueName.lowercase().startsWith(input.lowercase()) }
            }
    }
