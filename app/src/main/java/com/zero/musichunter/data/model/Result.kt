package com.zero.musichunter.data.model

import com.squareup.moshi.Json

data class Result(
    @Json(name = "artistName")
    val artistName: String,
    @Json(name = "artworkUrl100")
    val artworkUrl100: String,
    @Json(name = "previewUrl")
    val previewUrl: String,
    @Json(name = "collectionName")
    val collectionName: String?,
    @Json(name = "trackName")
    val trackName: String?,
)