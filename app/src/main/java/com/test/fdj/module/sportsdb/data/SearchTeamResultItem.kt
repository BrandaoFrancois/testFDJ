package com.test.fdj.module.sportsdb.data

import com.google.gson.annotations.SerializedName

data class SearchTeamResultItem(
    @SerializedName("strTeam")
    val name: String,
    @SerializedName("strTeamBadge")
    val teamBadge: String
)