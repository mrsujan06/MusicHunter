package com.zero.musichunter.data.remote

import com.squareup.moshi.Json
import com.zero.musichunter.data.database.DatabaseClassicMusic
import com.zero.musichunter.data.database.DatabasePopMusic
import com.zero.musichunter.data.database.DatabaseRockMusic
import com.zero.musichunter.data.domain.MusicResults

//result
data class NetworkMusic(
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

//Music response
data class NetworkMusicContainer(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results: List<NetworkMusic>
)

/**
 * Convert Network results to domain objects
 */
fun NetworkMusicContainer.asDomainModel(): List<MusicResults> {
    return results.map {
        MusicResults(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }

}

fun NetworkMusicContainer.asDatabaseModel(): List<DatabaseClassicMusic> {
    return results.map {
        DatabaseClassicMusic(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}

fun NetworkMusicContainer.asDatabasePopModel(): List<DatabasePopMusic> {
    return results.map {
        DatabasePopMusic(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}

fun NetworkMusicContainer.asDatabaseRockModel(): List<DatabaseRockMusic> {
    return results.map {
        DatabaseRockMusic(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}


