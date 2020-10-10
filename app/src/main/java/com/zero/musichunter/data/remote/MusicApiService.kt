package com.zero.musichunter.data.remote

import com.zero.musichunter.util.Constant
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET

interface MusicApiService {

    @GET(Constant.CLASSIC_MUSIC)
    fun getClassicMusic(): Flowable<NetworkMusicContainer>

    @GET(Constant.POP_MUSIC)
    fun getPopMusic(): Observable<NetworkMusicContainer>

    @GET(Constant.ROCK_MUSIC)
    fun getRockMusic(): Observable<NetworkMusicContainer>

}