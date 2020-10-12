package com.zero.musichunter.dagger

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zero.musichunter.App
import com.zero.musichunter.data.database.MusicDatabase
import com.zero.musichunter.data.remote.MusicApiService
import com.zero.musichunter.data.repository.MusicRepo
import com.zero.musichunter.data.repository.MusicRepoImp
import com.zero.musichunter.util.Constant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class AppModule(var app: App) {

    @Singleton
    @Provides
    open fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Singleton
    @Provides
    open fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    open fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    open fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    open fun provideMusicRepository(
        musicApiService: MusicApiService,
        database: MusicDatabase
    ): MusicRepo = MusicRepoImp(musicApiService, database)

    @Provides
    @Singleton
    open fun provideNetworkService(retrofit: Retrofit): MusicApiService =
        retrofit.create(MusicApiService::class.java)

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
    fun provideApp(): App = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext


}