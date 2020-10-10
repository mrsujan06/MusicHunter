package com.zero.musichunter.data.repository

import androidx.lifecycle.LiveData
import com.zero.musichunter.data.database.DatabaseMusic
import com.zero.musichunter.data.domain.MusicResults
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*

interface MusicRepo {
//   fun  getClassicMusic(): Observable<NetworkMusicContainer>
//  fun subscribeToDatabase(): Observer<in NetworkMusicContainer>

    //    fun getPopMusic(): Observable<NetworkMusicContainer>
//    fun getRockMusic(): Observable<NetworkMusicContainer>

    fun classicMusicObserver(): LiveData<List<MusicResults>>
    fun fetchClassicMusic(): Disposable


}