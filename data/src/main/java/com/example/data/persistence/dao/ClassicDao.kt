package com.example.data.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.persistence.dao.base.BaseDao
import com.example.data.persistence.entities.ClassicEntity

@Dao
abstract class ClassicDao : BaseDao<ClassicEntity> {

    /**
     * Select a repo by the id
     * @param id The repo id
     * @return A [ClassicEntity]
     */
    @Query("SELECT * FROM ${ClassicEntity.REPO_CLASSIC_TABLE} WHERE ${ClassicEntity.REPO_CLASSIC_ID} = :id")
    abstract fun get(id: String): ClassicEntity?

    /**
     * Select all repos
     * @return A list of [ClassicEntity] of all the repos in the table for user
     */
    @Query("SELECT * FROM ${ClassicEntity.REPO_CLASSIC_TABLE}")
    abstract fun getAll(): List<ClassicEntity>

}