package com.example.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.persistence.entities.RepoClassicEntity
import com.example.data.persistence.entities.RepoClassicEntity.Companion.REPO_CLASSIC_ID
import com.example.data.persistence.entities.RepoClassicEntity.Companion.REPO_CLASSIC_TABLE
import com.example.data.persistence.entities.RepoPopEntity
import com.example.data.persistence.entities.RepoRockEntity

@Dao
abstract class RepoDao {

    /**
     * Select a repo by the id
     * @param id The repo id
     * @return A [RepoClassicEntity]
     */
    @Query("SELECT * FROM $REPO_CLASSIC_TABLE WHERE $REPO_CLASSIC_ID = :id")
    abstract fun getClassic(id: String): RepoClassicEntity?

    /**
     * Select all repos
     * @return A list of [RepoClassicEntity] of all the repos in the table for user
     */
    @Query("SELECT * FROM $REPO_CLASSIC_TABLE")
    abstract fun getAllClassic(): List<RepoClassicEntity>

    /**
     * Select a repo by the id
     * @param id The repo id
     * @return A [RepoPopEntity]
     */
    @Query("SELECT * FROM ${RepoPopEntity.REPO_POP_TABLE} WHERE ${RepoPopEntity.REPO_POP_ID} = :id")
    abstract fun getPop(id: String): RepoPopEntity

    /**
     * Select all repos
     * @return A list of [RepoPopEntity] of all the repos in the table for user
     */
    @Query("SELECT * FROM ${RepoPopEntity.REPO_POP_TABLE}")
    abstract fun getAllPop(): List<RepoPopEntity>

    /**
     * Select a repo by the id
     * @param id The repo id
     * @return A [RepoRockEntity]
     */
    @Query("SELECT * FROM ${RepoRockEntity.REPO_ROCK_TABLE} WHERE ${RepoRockEntity.REPO_ROCK_ID} = :id")
    abstract fun getRock(id: String): RepoRockEntity?

    /**
     * Select all repos
     * @return A list of [RepoRockEntity] of all the repos in the table for user
     */
    @Query("SELECT * FROM ${RepoRockEntity.REPO_ROCK_TABLE}")
    abstract fun getAllRock(): List<RepoRockEntity>

    /**
     * Insert a entity into the table.
     * @param entity
     * @return The row ID of the newly inserted entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertClassic(entity: RepoClassicEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPop(entity: RepoPopEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRock(entity: RepoRockEntity): Long


}