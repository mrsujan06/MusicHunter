package com.zero.musichunter.domain.model

data class Repo(
    val artistName: String,
    val artworkUrl100: String,
    val previewUrl: String,
    val collectionName: String?,
    val trackName: String?,
)