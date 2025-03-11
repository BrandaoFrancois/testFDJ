package com.test.fdj.module.sportsdb.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchTeamResult(
    @SerialName("teams")
    val teams: List<SearchTeamResultItem>,
)
