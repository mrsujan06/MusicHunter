package com.example.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.persistence.entities.PopEntity.Companion.REPO_POP_TABLE

/**
 * RepoPopEntity represents a pop music entity in the database.
 */
@Entity(tableName = REPO_POP_TABLE)
data class PopEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = REPO_POP_ID)
    val previewUrl: String,
    @ColumnInfo(name = REPO_POP_ARTIST_NAME)
    val artistName: String,
    @ColumnInfo(name = REPO_POP_ARTWORK_URL)
    val artworkUrl100: String,
    @ColumnInfo(name = REPO_POP_COLLECTION_NAME)
    val collectionName: String?,
    @ColumnInfo(name = REPO_POP_TRACK_NAME)
    val trackName: String?,
) {
    companion object {
        // TABLE
        const val REPO_POP_TABLE = "repo_pop"

        // COLUMN
        const val REPO_POP_ID = "id"
        const val REPO_POP_ARTIST_NAME = "artist_name"
        const val REPO_POP_ARTWORK_URL = "artwork_url"
        const val REPO_POP_COLLECTION_NAME = "collection_name"
        const val REPO_POP_TRACK_NAME = "track_name"
    }
}
