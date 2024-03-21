package com.test.fdj.module.sportsdb.data

import com.google.gson.annotations.SerializedName

data class SearchTeamResult (
    @SerializedName("teams")
    val teams : List<SearchTeamResultItem>
)