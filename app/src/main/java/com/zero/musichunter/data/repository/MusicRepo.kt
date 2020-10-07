package com.zero.musichunter.data.repository

import com.zero.musichunter.data.model.MusicResponse
import io.reactivex.Observable

interface MusicRepo {
    fun getClassicMusic(): Observable<MusicResponse>
    fun getPopMusic(): Observable<MusicResponse>
    fun getRockMusic(): Observable<MusicResponse>
}