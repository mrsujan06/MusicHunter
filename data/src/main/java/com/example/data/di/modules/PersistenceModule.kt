package com.example.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.persistence.dao.RepoDao
import com.example.data.persistence.database.MusicDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class PersistenceModule {

    @Provides
    @Singleton
    open fun provideRoomDatabase(context: Context): MusicDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MusicDatabase::class.java,
            "Musics"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    open fun provideRepoDao(musicDatabase: MusicDatabase): RepoDao =
        musicDatabase.musicDao


}