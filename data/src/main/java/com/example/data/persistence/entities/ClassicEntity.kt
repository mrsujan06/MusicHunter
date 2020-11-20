package com.example.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.persistence.entities.ClassicEntity.Companion.REPO_CLASSIC_TABLE

/**
 * RepoClassicEntity represents a music classic entity in the database.
 */
@Entity(tableName = REPO_CLASSIC_TABLE)
data class ClassicEntity constructor(

    @PrimaryKey
    @ColumnInfo(name = REPO_CLASSIC_ID)
    val previewUrl: String,
    @ColumnInfo(name = REPO_CLASSIC_ARTIST_NAME)
    val artistName: String,
    @ColumnInfo(name = REPO_CLASSIC_ARTWORK_URL)
    val artworkUrl100: String,
    @ColumnInfo(name = REPO_CLASSIC_COLLECTION_NAME)
    val collectionName: String?,
    @ColumnInfo(name = REPO_CLASSIC_TRACK_NAME)
    val trackName: String?,
)  {
    companion object {
        // TABLE
        const val REPO_CLASSIC_TABLE = "repo_classic"

        // COLUMN
        const val REPO_CLASSIC_ID = "id"
        const val REPO_CLASSIC_ARTIST_NAME = "artist_name"
        const val REPO_CLASSIC_ARTWORK_URL = "artwork_url"
        const val REPO_CLASSIC_COLLECTION_NAME = "collection_name"
        const val REPO_CLASSIC_TRACK_NAME = "track_name"
    }

}