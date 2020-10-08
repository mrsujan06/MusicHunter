package com.zero.musichunter.data.repository

import com.zero.musichunter.data.remote.MusicApiService
import com.zero.musichunter.data.remote.NetworkMusicContainer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MusicRepoImp(var musicApiService: MusicApiService) : MusicRepo {

    //Fetch classic music from remote
    override fun getClassicMusic(): Observable<NetworkMusicContainer> =
        musicApiService.getClassicMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //Fetch Pop Music from remote
    override fun getPopMusic(): Observable<NetworkMusicContainer> =
        musicApiService.getPopMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //Fetch Rock Music from remote
    override fun getRockMusic(): Observable<NetworkMusicContainer> =
        musicApiService.getRockMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}