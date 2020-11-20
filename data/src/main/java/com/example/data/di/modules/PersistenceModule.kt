package com.example.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.persistence.dao.ClassicDao
import com.example.data.persistence.dao.PopDao
import com.example.data.persistence.dao.RockDao
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
    open fun provideClassicDao(musicDatabase: MusicDatabase): ClassicDao =
        musicDatabase.classicDao

    @Provides
    @Singleton
    open fun providePopDao(musicDatabase: MusicDatabase): PopDao =
        musicDatabase.popDao

    @Provides
    @Singleton
    open fun provideRockDao(musicDatabase: MusicDatabase): RockDao =
        musicDatabase.rockDao
}