package com.zero.musichunter.data.domain

data class MusicResults(
    val artistName: String,
    val artworkUrl100: String,
    val previewUrl: String,
    val collectionName: String?,
    val trackName: String?,
)