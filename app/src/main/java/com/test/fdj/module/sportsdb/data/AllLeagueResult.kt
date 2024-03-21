package com.test.fdj.module.sportsdb.data

import com.google.gson.annotations.SerializedName

data class AllLeagueResult (
    @SerializedName("leagues")
    val leagues : List<AllLeagueResultItem>
)