package com.example.data.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.persistence.dao.base.BaseDao
import com.example.data.persistence.entities.PopEntity

@Dao
abstract class PopDao : BaseDao<PopEntity> {
    /**
     * Select a repo by the id
     * @param id The repo id
     * @return A [PopEntity]
     */
    @Query("SELECT * FROM ${PopEntity.REPO_POP_TABLE} WHERE ${PopEntity.REPO_POP_ID} = :id")
    abstract fun get(id: String): PopEntity

    /**
     * Select all repos
     * @return A list of [PopEntity] of all the repos in the table for user
     */
    @Query("SELECT * FROM ${PopEntity.REPO_POP_TABLE}")
    abstract fun getAll(): List<PopEntity>

}