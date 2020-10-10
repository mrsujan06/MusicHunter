package com.zero.musichunter.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MusicDao {

    @Query("select * from databasemusic")
    fun getClassicMusic(): LiveData<List<DatabaseMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(music: List<DatabaseMusic>)
}

@Database(entities = [DatabaseMusic::class], version = 1)
abstract class MusicDatabase : RoomDatabase() {
    abstract val musicDao: MusicDao
}
