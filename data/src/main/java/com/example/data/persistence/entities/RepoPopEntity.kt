package com.example.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.persistence.entities.RepoPopEntity.Companion.REPO_POP_TABLE
import com.zero.musichunter.domain.model.Repo

/**
 * RepoPopEntity represents a pop music entity in the database.
 */
@Entity(tableName = REPO_POP_TABLE)
data class RepoPopEntity constructor(
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

//region RepoPopEntity to List of Repo
/**
 * [RepoPopEntity] extension function to
 * convert Network results to list of [Repo]
 * domain objects
 */
fun List<RepoPopEntity>.asDomainPopModel(): List<Repo> {
    return map {
        Repo(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}
//end region