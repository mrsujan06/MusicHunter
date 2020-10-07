package com.zero.musichunter.testDagger

import com.squareup.moshi.Moshi
import com.zero.musichunter.dagger.AppModule
import com.zero.musichunter.data.remote.MusicApiService
import com.zero.musichunter.data.repository.MusicRepo
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class TestAppModule : AppModule() {
    override fun provideMusicRepository(musicApiService: MusicApiService): MusicRepo = mockk()
    override fun provideMoshi(): Moshi = mockk()
    override fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = mockk()
    override fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        mockk()

    override fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = mockk()
    override fun provideNetworkService(retrofit: Retrofit): MusicApiService = mockk()
}