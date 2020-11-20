package com.example.data.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.persistence.dao.ClassicDao
import com.example.data.persistence.dao.PopDao
import com.example.data.persistence.dao.RockDao
import com.example.data.persistence.entities.ClassicEntity
import com.example.data.persistence.entities.PopEntity
import com.example.data.persistence.entities.RockEntity

@Database(
    entities = [ClassicEntity::class, PopEntity::class, RockEntity::class],
    version = 3, exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
//    abstract val musicDao: RepoDao
    abstract val classicDao: ClassicDao
    abstract val popDao: PopDao
    abstract val rockDao: RockDao

}
