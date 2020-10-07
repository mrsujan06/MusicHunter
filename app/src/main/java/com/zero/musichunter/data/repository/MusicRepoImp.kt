package com.zero.musichunter.data.repository

import com.zero.musichunter.data.model.MusicResponse
import com.zero.musichunter.data.remote.MusicApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MusicRepoImp(var musicApiService: MusicApiService) : MusicRepo {

    //Fetch classic music from remote
    override fun getClassicMusic(): Observable<MusicResponse> =
        musicApiService.getClassicMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //Fetch Pop Music from remote
    override fun getPopMusic(): Observable<MusicResponse> =
        musicApiService.getPopMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //Fetch Rock Music from remote
    override fun getRockMusic(): Observable<MusicResponse> =
        musicApiService.getRockMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}