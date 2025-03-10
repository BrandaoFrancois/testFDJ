package com.test.fdj.module.sportsdb.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchTeamResultItem(
    @SerialName("strTeam")
    val name: String,
    @SerialName("strBadge")
    val teamBadge: String
)