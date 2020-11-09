package com.example.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.persistence.entities.RepoClassicEntity.Companion.REPO_CLASSIC_TABLE
import com.zero.musichunter.domain.model.Repo

/**
 * RepoClassicEntity represents a music classic entity in the database.
 */
@Entity(tableName = REPO_CLASSIC_TABLE)
data class RepoClassicEntity constructor(
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
) {
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

//region map RepoClassicEntity to List of Repo
/**
 * [RepoClassicEntity] extension function to
 * convert Network results to list of [Repo]
 * domain objects
 */
fun List<RepoClassicEntity>.asDomainModel(): List<Repo> {
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
//end