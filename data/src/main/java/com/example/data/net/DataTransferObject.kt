package com.example.data.net

import com.example.data.persistence.entities.RepoClassicEntity
import com.example.data.persistence.entities.RepoPopEntity
import com.example.data.persistence.entities.RepoRockEntity
import com.squareup.moshi.Json
import com.zero.musichunter.domain.model.Repo

//RepoDTO
data class RepoDTO(
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

//RepoDtoContainer
data class NetworkMusicContainer(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results: List<RepoDTO>
)

//region map DTO to List of Repo
/**
 * [NetworkMusicContainer] extension function to
 * convert Network results to list of [Repo] domain objects
 */
fun NetworkMusicContainer.asDomainModel(): List<Repo> {
    return results.map {
        Repo(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}
//endregion

//region map DTO to List of RepoClassicEntity
/**
 * [NetworkMusicContainer] extension function to
 * convert Network results to list of [RepoClassicEntity]
 * entity objects
 */
fun NetworkMusicContainer.asDatabaseClassicModel(): List<RepoClassicEntity> {
    return results.map {
        RepoClassicEntity(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}
//endregion

//region map DTO to List of RepoPopEntity
/**
 * [NetworkMusicContainer] extension function to
 * convert Network results to list of [RepoPopEntity]
 * entity objects
 */
fun NetworkMusicContainer.asDatabasePopModel(): List<RepoPopEntity> {
    return results.map {
        RepoPopEntity(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}
//endregion

//region map DTO to List of RepoRockEntity
/**
 * [NetworkMusicContainer] extension function to
 * convert network results to list of [RepoRockEntity]
 * entity objects
 */
fun NetworkMusicContainer.asDatabaseRockModel(): List<RepoRockEntity> {
    return results.map {
        RepoRockEntity(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}
//endregion




