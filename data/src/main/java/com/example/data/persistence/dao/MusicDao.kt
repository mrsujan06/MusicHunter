package com.example.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.persistence.entities.RepoClassicEntity
import com.example.data.persistence.entities.RepoClassicEntity.Companion.REPO_CLASSIC_TABLE
import com.example.data.persistence.entities.RepoPopEntity
import com.example.data.persistence.entities.RepoPopEntity.Companion.REPO_POP_TABLE
import com.example.data.persistence.entities.RepoRockEntity
import com.example.data.persistence.entities.RepoRockEntity.Companion.REPO_ROCK_TABLE
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MusicDao {

    @Query("SELECT * FROM $REPO_CLASSIC_TABLE")
    fun getClassicMusic(): Single<List<RepoClassicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClassicMusic(classicEntities: List<RepoClassicEntity>): Completable

    @Query("SELECT * FROM $REPO_POP_TABLE")
    fun getPopMusic(): Single<List<RepoPopEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopMusic(popEntities: List<RepoPopEntity>): Completable

    @Query("SELECT * FROM $REPO_ROCK_TABLE")
    fun getRockMusic(): Single<List<RepoRockEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRockMusic(rockEntities: List<RepoRockEntity>): Completable

}