package com.test.fdj.api

import com.google.gson.annotations.SerializedName

data class SearchTeamResult (
    @SerializedName("teams")
    val teams : List<SearchTeamResultItem>
)