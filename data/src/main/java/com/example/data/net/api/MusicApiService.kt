package com.example.data.net.api

import com.example.data.net.dto.NetworkMusicContainer
import com.example.data.utils.Constant
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MusicApiService {

    @GET(Constant.CLASSIC_MUSIC)
    fun getClassicMusic(): Single<NetworkMusicContainer>

    @GET(Constant.POP_MUSIC)
    fun getPopMusic(): Single<NetworkMusicContainer>

    @GET(Constant.ROCK_MUSIC)
    fun getRockMusic(): Single<NetworkMusicContainer>

}