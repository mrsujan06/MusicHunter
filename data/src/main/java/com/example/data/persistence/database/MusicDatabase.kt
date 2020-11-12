package com.example.data.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.persistence.dao.RepoDao
import com.example.data.persistence.entities.RepoClassicEntity
import com.example.data.persistence.entities.RepoPopEntity
import com.example.data.persistence.entities.RepoRockEntity

@Database(
    entities = [RepoClassicEntity::class, RepoPopEntity::class, RepoRockEntity::class],
    version = 3, exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
    abstract val musicDao: RepoDao
}
