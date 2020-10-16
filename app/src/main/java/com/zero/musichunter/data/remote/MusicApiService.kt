package com.zero.musichunter.data.remote

import com.zero.musichunter.util.Constant
import retrofit2.http.GET

interface MusicApiService {

    @GET(Constant.CLASSIC_MUSIC)
    suspend fun getClassicMusic(): NetworkMusicContainer

    @GET(Constant.POP_MUSIC)
    suspend fun getPopMusic(): NetworkMusicContainer

    @GET(Constant.ROCK_MUSIC)
    suspend fun getRockMusic(): NetworkMusicContainer

}