package com.test.fdj.api

import com.google.gson.annotations.SerializedName

data class AllLeagueResultItem (
    @SerializedName("strLeague")
    val leagueName: String,
)