package com.zero.musichunter.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable

@Dao
interface MusicDao {

    @Query("select * from databaseclassicmusic")
    fun getClassicMusic(): LiveData<List<DatabaseClassicMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClassicMusic(music: List<DatabaseClassicMusic>): Completable

    @Query("select * from databasepopmusic")
    fun getPopMusic(): LiveData<List<DatabasePopMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopMusic(music: List<DatabasePopMusic>): Completable

    @Query("select * from databaserockmusic")
    fun getRockMusic(): LiveData<List<DatabaseRockMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRockMusic(music: List<DatabaseRockMusic>): Completable
}

@Database(
    entities = [DatabaseClassicMusic::class, DatabasePopMusic::class, DatabaseRockMusic::class],
    version = 2
)
abstract class MusicDatabase : RoomDatabase() {
    abstract val musicDao: MusicDao
}
