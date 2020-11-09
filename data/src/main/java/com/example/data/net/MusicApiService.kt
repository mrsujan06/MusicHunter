package com.example.data.net

import com.example.data.utils.Constant
import io.reactivex.Single
import retrofit2.http.GET

interface MusicApiService {

    @GET(Constant.CLASSIC_MUSIC)
    fun getClassicMusic(): Single<NetworkMusicContainer>

    @GET(Constant.POP_MUSIC)
    fun getPopMusic(): Single<NetworkMusicContainer>

    @GET(Constant.ROCK_MUSIC)
    fun getRockMusic(): Single<NetworkMusicContainer>

}