package com.example.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.persistence.entities.RockEntity.Companion.REPO_ROCK_TABLE

/**
 * RepoRockEntity represents a Rock music entity in the database.
 */
@Entity(tableName = REPO_ROCK_TABLE)
data class RockEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = REPO_ROCK_ID)
    val previewUrl: String,
    @ColumnInfo(name = REPO_ROCK_ARTIST_NAME)
    val artistName: String,
    @ColumnInfo(name = REPO_ROCK_ARTWORK_URL)
    val artworkUrl100: String,
    @ColumnInfo(name = REPO_ROCK_COLLECTION_NAME)
    val collectionName: String?,
    @ColumnInfo(name = REPO_ROCK_TRACK_NAME)
    val trackName: String?,
) {
    companion object {
        // TABLE
        const val REPO_ROCK_TABLE = "repo_rock"

        // COLUMN
        const val REPO_ROCK_ID = "id"
        const val REPO_ROCK_ARTIST_NAME = "artist_name"
        const val REPO_ROCK_ARTWORK_URL = "artwork_url"
        const val REPO_ROCK_COLLECTION_NAME = "collection_name"
        const val REPO_ROCK_TRACK_NAME = "track_name"
    }
}

