package com.test.fdj.usecase

interface GetLeaguesFilteredByUseCase {
    suspend fun execute(input: String): List<String>
}