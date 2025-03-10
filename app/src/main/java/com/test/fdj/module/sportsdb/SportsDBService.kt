package com.test.fdj.module.sportsdb

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.test.fdj.BuildConfig
import com.test.fdj.module.sportsdb.data.AllLeagueResult
import com.test.fdj.module.sportsdb.data.SearchTeamResult
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsDBService {
    @GET("$API_KEY/all_leagues.php")
    suspend fun getListOfAllLeagues(): AllLeagueResult

    @GET("$API_KEY/search_all_teams.php")
    suspend fun searchTeams(
        @Query("l") leagueNameFilter: String
    ) : SearchTeamResult

    companion object {
        private const val BASE_URL = BuildConfig.SPORTSDB_BASE_URL
        private const val API_KEY = BuildConfig.SPORTSDB_API_KEY

        fun create() : SportsDBService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val networkJson = Json { ignoreUnknownKeys = true }

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(SportsDBService::class.java)
        }
    }
}
