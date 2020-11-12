package com.example.data.mapper

import com.example.data.net.dto.RepoDTO
import com.example.data.persistence.entities.RepoClassicEntity
import com.example.data.persistence.entities.RepoPopEntity
import com.example.data.persistence.entities.RepoRockEntity
import com.zero.musichunter.domain.model.Repo
import javax.inject.Inject

/**
 * Mapper class used to transform [RepoDTO] or
 * RepoEntities such as [RepoClassicEntity] , [RepoPopEntity],
 * [RepoRockEntity] to [Repo] in the domain layer and vice versa
 * */

class RepoMapper
@Inject constructor() {

    //region DTO to MODEL
    /**
     *Transform a [RepoDTO] into [Repo].
     * @param dto Object to be transformed.
     * @return [Repo] if valid [RepoDTO]
     */
    private fun transform(dto: RepoDTO): Repo =
        Repo(
            dto.artistName, dto.artworkUrl100, dto.previewUrl,
            dto.collectionName, dto.trackName
        )

    /**
     * Transform [RepoClassicEntity],[RepoPopEntity],[RepoRockEntity]
     * into an [Repo].
     * @param dtoCollection Object Collection to be transformed.
     * @return list of [Repo]
     * **/
    fun transform(dtoCollection: Collection<RepoDTO>): List<Repo> =
        dtoCollection.map {
            transform(it)
        }
    //end region

    //region classic ENTITY to MODEL
    /**
     * Transform a [RepoClassicEntity] into a [Repo].
     * @param entity Object to be transformed.
     * @return [Repo] if valid [RepoClassicEntity] otherwise null.
     */
    private fun transformClassicToRepo(entity: RepoClassicEntity): Repo =
        Repo(
            entity.artistName,
            entity.artworkUrl100,
            entity.previewUrl,
            entity.collectionName,
            entity.trackName
        )

    /**
     * Transform a Collection of [RepoClassicEntity] into a List of [Repo].
     * @param entityClassicCollection Object collection to be transformed.
     * @return list of [Repo].
     */
    fun transformClassicEntityToRepo(entityClassicCollection: Collection<RepoClassicEntity>): List<Repo> =
        entityClassicCollection.map {
            transformClassicToRepo(it)
        }
    //end region

    //region pop ENTITY to MODEL
    /**
     * Transform a [RepoPopEntity] into a [Repo].
     * @param entity Object to be transformed.
     * @return [Repo] if valid [RepoPopEntity] otherwise null.
     */
    private fun transformPopToRepo(entity: RepoPopEntity): Repo =
        Repo(
            entity.artistName,
            entity.artworkUrl100,
            entity.previewUrl,
            entity.collectionName,
            entity.trackName
        )

    /**
     * Transform a Collection of [RepoPopEntity] into a List of [Repo].
     * @param entityPopCollection Object collection to be transformed.
     * @return list of [Repo].
     */
    fun transformPopToRepoList(entityPopCollection: Collection<RepoPopEntity>): List<Repo> =
        entityPopCollection.map {
            transformPopToRepo(it)
        }
    //end region

    //region rock ENTITY to MODEL
    /**
     * Transform a [RepoRockEntity] into a [Repo].
     * @param entity Object to be transformed.
     * @return [Repo] if valid [RepoRockEntity] otherwise null.
     */
    private fun transformRockToRepo(entity: RepoRockEntity): Repo {
        return Repo(
            entity.artistName,
            entity.artworkUrl100,
            entity.previewUrl,
            entity.collectionName,
            entity.trackName
        )
    }

    /**
     * Transform a Collection of [RepoRockEntity] into a List of [Repo].
     * @param entityRockCollection Object collection to be transformed.
     * @return list of [Repo].
     */
    fun transformRockToRepoList(entityRockCollection: Collection<RepoRockEntity>): List<Repo> {
        return entityRockCollection.map {
            transformRockToRepo(it)
        }
    }
    //end region

    //region MODEL to classic ENTITY
    /**
     * Transform a [Repo] into an [RepoClassicEntity].
     * @param model Object to be transformed.
     * @return [Repo] if valid [RepoEntity] otherwise null.
     */
    fun transformToClassicEntity(model: Repo): RepoClassicEntity =
        RepoClassicEntity(
            model.previewUrl,
            model.artistName,
            model.artworkUrl100,
            model.collectionName,
            model.trackName
        )

    /**
     * Transform a Collection of [Repo] into a List of [RepoClassicEntity].
     * @param modelCollection Object Collection to be transformed.
     * @return list of [RepoClassicEntity]
     */
    fun transformToClassicEntityList(modelCollection: Collection<Repo>): List<RepoClassicEntity> =
        modelCollection.map {
            transformToClassicEntity(it)
        }
    //end region

    //region MODEL to pop ENTITY
    /**
     * Transform a [Repo] into an [RepoPopEntity].
     * @param model Object to be transformed.
     * @return [Repo] if valid [RepoPopEntity] otherwise null.
     */
    fun transformToPopEntity(model: Repo): RepoPopEntity =
        RepoPopEntity(
            model.previewUrl,
            model.artistName,
            model.artworkUrl100,
            model.collectionName,
            model.trackName
        )

    /**
     * Transform a Collection of [Repo] into a List of [RepoPopEntity].
     * @param modelCollection Object Collection to be transformed.
     * @return list of [RepoPopEntity]
     */
    fun transformToPopEntity(modelCollection: Collection<Repo>): List<RepoPopEntity> =
        modelCollection.map {
            transformToPopEntity(it)
        }
    //end region

    //region MODEL to rock ENTITY
    /**
     * Transform a [Repo] into an [RepoRockEntity].
     * @param model Object to be transformed.
     * @return [Repo] if valid [RepoRockEntity] otherwise null.
     */
    fun transformToRockEntity(model: Repo): RepoRockEntity {
        return RepoRockEntity(
            model.previewUrl,
            model.artistName,
            model.artworkUrl100,
            model.collectionName,
            model.trackName
        )
    }

    /**
     * Transform a Collection of [Repo] into a List of [RepoRockEntity].
     * @param modelCollection Object Collection to be transformed.
     * @return list of [RepoRockEntity]
     */
    fun transformToRockEntity(modelCollection: Collection<Repo>): List<RepoRockEntity> =
        modelCollection.map {
            transformToRockEntity(it)
        }
    //end region

}