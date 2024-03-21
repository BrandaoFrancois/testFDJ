package com.test.fdj.module.sportsdb.data

import com.google.gson.annotations.SerializedName

data class AllLeagueResultItem (
    @SerializedName("strLeague")
    val leagueName: String,
)