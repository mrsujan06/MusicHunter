package com.example.data.mapper

import com.example.data.net.dto.RepoDTO
import com.example.data.persistence.entities.ClassicEntity
import com.example.data.persistence.entities.PopEntity
import com.example.data.persistence.entities.RockEntity
import com.zero.musichunter.domain.model.Repo
import javax.inject.Inject

/**
 * Mapper class used to transform [RepoDTO] or
 * RepoEntities such as [ClassicEntity] , [PopEntity],
 * [RockEntity] to [Repo] in the domain layer and vice versa
 * */

class RepoMapper
@Inject constructor() {

    //region Net DTO to domain MODEL
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
     * Transform [ClassicEntity],[PopEntity],[RockEntity]
     * into an [Repo].
     * @param dtoCollection Object Collection to be transformed.
     * @return list of [Repo]
     * **/
    fun transform(dtoCollection: Collection<RepoDTO>): List<Repo> =
        dtoCollection.map {
            transform(it)
        }
    //end region

    //region classic database ENTITY to domain MODEL
    /**
     * Transform a [ClassicEntity] into a [Repo].
     * @param entity Object to be transformed.
     * @return [Repo] if valid [ClassicEntity] otherwise null.
     */
    private fun transformClassicToRepo(entity: ClassicEntity): Repo =
        Repo(
            entity.artistName,
            entity.artworkUrl100,
            entity.previewUrl,
            entity.collectionName,
            entity.trackName
        )

    /**
     * Transform a Collection of [ClassicEntity] into a List of [Repo].
     * @param entityClassicCollection Object collection to be transformed.
     * @return list of [Repo].
     */
    fun transformClassicEntityToRepo(entityClassicCollection: Collection<ClassicEntity>): List<Repo> =
        entityClassicCollection.map {
            transformClassicToRepo(it)
        }
    //endregion

    //region pop database ENTITY to domain MODEL
    /**
     * Transform a [PopEntity] into a [Repo].
     * @param entity Object to be transformed.
     * @return [Repo] if valid [PopEntity] otherwise null.
     */
    private fun transformPopToRepo(entity: PopEntity): Repo =
        Repo(
            entity.artistName,
            entity.artworkUrl100,
            entity.previewUrl,
            entity.collectionName,
            entity.trackName
        )

    /**
     * Transform a Collection of [PopEntity] into a List of [Repo].
     * @param entityPopCollection Object collection to be transformed.
     * @return list of [Repo].
     */
    fun transformPopToRepoList(entityPopCollection: Collection<PopEntity>): List<Repo> =
        entityPopCollection.map {
            transformPopToRepo(it)
        }
    //end region

    //region rock database ENTITY to domain MODEL
    /**
     * Transform a [RockEntity] into a [Repo].
     * @param entity Object to be transformed.
     * @return [Repo] if valid [RockEntity] otherwise null.
     */
    private fun transformRockToRepo(entity: RockEntity): Repo {
        return Repo(
            entity.artistName,
            entity.artworkUrl100,
            entity.previewUrl,
            entity.collectionName,
            entity.trackName
        )
    }

    /**
     * Transform a Collection of [RockEntity] into a List of [Repo].
     * @param entityRockCollection Object collection to be transformed.
     * @return list of [Repo].
     */
    fun transformRockToRepoList(entityRockCollection: Collection<RockEntity>): List<Repo> {
        return entityRockCollection.map {
            transformRockToRepo(it)
        }
    }
    //end region

    //region Domain MODEL to classic database ENTITY
    /**
     * Transform a [Repo] into an [ClassicEntity].
     * @param model Object to be transformed.
     * @return [Repo] if valid [ClassicEntity] otherwise null.
     */
    fun transformToClassicEntity(model: Repo): ClassicEntity =
        ClassicEntity(
            model.previewUrl,
            model.artistName,
            model.artworkUrl100,
            model.collectionName,
            model.trackName
        )

    /**
     * Transform a Collection of [Repo] into a List of [ClassicEntity].
     * @param modelCollection Object Collection to be transformed.
     * @return list of [ClassicEntity]
     */
    fun transformToClassicEntityList(modelCollection: Collection<Repo>): List<ClassicEntity> =
        modelCollection.map {
            transformToClassicEntity(it)
        }
    //end region

    //region domain MODEL to pop database ENTITY
    /**
     * Transform a [Repo] into an [PopEntity].
     * @param model Object to be transformed.
     * @return [Repo] if valid [PopEntity] otherwise null.
     */
    fun transformToPopEntity(model: Repo): PopEntity =
        PopEntity(
            model.previewUrl,
            model.artistName,
            model.artworkUrl100,
            model.collectionName,
            model.trackName
        )

    /**
     * Transform a Collection of [Repo] into a List of [PopEntity].
     * @param modelCollection Object Collection to be transformed.
     * @return list of [PopEntity]
     */
    fun transformToPopEntity(modelCollection: Collection<Repo>): List<PopEntity> =
        modelCollection.map {
            transformToPopEntity(it)
        }
    //end region

    //region domain MODEL to rock database ENTITY
    /**
     * Transform a [Repo] into an [RockEntity].
     * @param model Object to be transformed.
     * @return [Repo] if valid [RockEntity] otherwise null.
     */
    fun transformToRockEntity(model: Repo): RockEntity {
        return RockEntity(
            model.previewUrl,
            model.artistName,
            model.artworkUrl100,
            model.collectionName,
            model.trackName
        )
    }

    /**
     * Transform a Collection of [Repo] into a List of [RockEntity].
     * @param modelCollection Object Collection to be transformed.
     * @return list of [RockEntity]
     */
    fun transformToRockEntity(modelCollection: Collection<Repo>): List<RockEntity> =
        modelCollection.map {
            transformToRockEntity(it)
        }
    //end region

}