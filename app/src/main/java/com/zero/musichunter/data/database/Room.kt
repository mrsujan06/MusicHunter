package com.zero.musichunter.data.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MusicDao {

    @Query("select * from databaseclassicmusic")
    fun getClassicMusic(): LiveData<List<DatabaseClassicMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(classicMusics: List<DatabaseClassicMusic>)

    @Query("select * from databasepopmusic")
    fun getPopMusic(): LiveData<List<DatabasePopMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopMusic(popMusics: List<DatabasePopMusic>)

    @Query("select * from databaserockmusic")
    fun getRockMusic(): LiveData<List<DatabaseRockMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRockMusic(popMusics: List<DatabaseRockMusic>)

}

@Database(
    entities = [DatabaseClassicMusic::class, DatabasePopMusic::class, DatabaseRockMusic::class],
    version = 3
)
abstract class MusicDatabase : RoomDatabase() {
    abstract val musicDao: MusicDao
}