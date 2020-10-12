package com.zero.musichunter.data.repository

import androidx.lifecycle.LiveData
import com.zero.musichunter.data.domain.MusicResults
import io.reactivex.disposables.Disposable

interface MusicRepo {

    fun classicMusicObserver(): LiveData<List<MusicResults>>
    fun popMusicObserver(): LiveData<List<MusicResults>>
    fun rockMusicObserver(): LiveData<List<MusicResults>>
    fun fetchClassicMusic(): Disposable
    fun fetchPopMusic(): Disposable
    fun fetchRockMusic(): Disposable
}