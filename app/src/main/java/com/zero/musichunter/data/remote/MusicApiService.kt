package com.zero.musichunter.data.remote

import com.zero.musichunter.data.model.MusicResponse
import com.zero.musichunter.util.Constant
import io.reactivex.Observable
import retrofit2.http.GET

interface MusicApiService {

    @GET(Constant.CLASSIC_MUSIC)
    fun getClassicMusic(): Observable<MusicResponse>

    @GET(Constant.POP_MUSIC)
    fun getPopMusic(): Observable<MusicResponse>

    @GET(Constant.ROCK_MUSIC)
    fun getRockMusic(): Observable<MusicResponse>

}