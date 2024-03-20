package com.test.fdj.api

import com.google.gson.annotations.SerializedName

data class AllLeagueResult (
    @SerializedName("leagues")
    val leagues : List<AllLeagueResultItem>
)