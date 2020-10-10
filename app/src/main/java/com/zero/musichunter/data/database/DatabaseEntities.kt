package com.zero.musichunter.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zero.musichunter.data.domain.MusicResults


/**
 * DatabaseMusic represents a music entity in the database.
 */
@Entity
data class DatabaseMusic constructor(
    @PrimaryKey
    val previewUrl: String,
    val artistName: String,
    val artworkUrl100: String,
    val collectionName: String?,
    val trackName: String?,
)

/**
 * Map DatabaseMusic to domain entities
 */
fun List<DatabaseMusic>.asDomainModel(): List<MusicResults> {
    return map {
        MusicResults(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}