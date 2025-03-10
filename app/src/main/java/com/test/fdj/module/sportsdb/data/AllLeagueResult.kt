package com.test.fdj.module.sportsdb.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllLeagueResult (
    @SerialName("leagues")
    val leagues: List<AllLeagueResultItem>
)
