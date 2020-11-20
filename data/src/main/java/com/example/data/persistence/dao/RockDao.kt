package com.example.data.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.persistence.dao.base.BaseDao
import com.example.data.persistence.entities.RockEntity

@Dao
abstract class RockDao : BaseDao<RockEntity> {
    /**
     * Select a repo by the id
     * @param id The repo id
     * @return A [RockEntity]
     */
    @Query("SELECT * FROM ${RockEntity.REPO_ROCK_TABLE} WHERE ${RockEntity.REPO_ROCK_ID} = :id")
   abstract fun get(id: String): RockEntity?

    /**
     * Select all repos
     * @return A list of [RockEntity] of all the repos in the table for user
     */
    @Query("SELECT * FROM ${RockEntity.REPO_ROCK_TABLE}")
    abstract fun getAll(): List<RockEntity>
}