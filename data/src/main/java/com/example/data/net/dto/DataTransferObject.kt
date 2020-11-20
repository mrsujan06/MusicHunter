package com.example.data.net.dto

import com.example.data.persistence.entities.ClassicEntity
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
 * convert Network results to list of [ClassicEntity]
 * entity objects
 */
fun NetworkMusicContainer.asDatabaseClassicModel(): List<ClassicEntity> {
    return results.map {
        ClassicEntity(
            previewUrl = it.previewUrl,
            artistName = it.artistName,
            artworkUrl100 = it.artworkUrl100,
            collectionName = it.collectionName,
            trackName = it.trackName
        )
    }
}
//endregion



