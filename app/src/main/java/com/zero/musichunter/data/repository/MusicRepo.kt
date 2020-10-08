package com.zero.musichunter.data.repository

import com.zero.musichunter.data.remote.NetworkMusicContainer
import io.reactivex.Observable

interface MusicRepo {
    fun getClassicMusic(): Observable<NetworkMusicContainer>
    fun getPopMusic(): Observable<NetworkMusicContainer>
    fun getRockMusic(): Observable<NetworkMusicContainer>
}