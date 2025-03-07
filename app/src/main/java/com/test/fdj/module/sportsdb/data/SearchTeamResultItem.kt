package com.test.fdj.module.sportsdb.data

import com.google.gson.annotations.SerializedName

data class SearchTeamResultItem(
    @SerializedName("strTeam")
    val name: String,
    @SerializedName("strBadge")
    val teamBadge: String
)