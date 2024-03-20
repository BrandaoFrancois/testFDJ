package com.test.fdj.api

import com.google.gson.annotations.SerializedName

data class SearchTeamResultItem(
    @SerializedName("strTeam")
    val name: String,
    @SerializedName("strTeamBadge")
    val teamBadge: String
)