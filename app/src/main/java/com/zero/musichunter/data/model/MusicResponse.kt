package com.zero.musichunter.data.model


import com.squareup.moshi.Json

data class MusicResponse(

    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results: List<Result>

)