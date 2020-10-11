package com.zero.musichunter.data.repository

import androidx.lifecycle.LiveData
import com.zero.musichunter.data.domain.MusicResults

interface MusicRepo {
    suspend fun getClassicMusic()

    suspend fun getPopMusic()

    suspend fun getRockMusic()

    fun classicMusicObservable(): LiveData<List<MusicResults>>

    fun popMusicObservable(): LiveData<List<MusicResults>>

    fun rockMusicObservable(): LiveData<List<MusicResults>>
}