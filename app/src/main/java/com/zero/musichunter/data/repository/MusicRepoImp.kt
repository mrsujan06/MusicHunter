package com.zero.musichunter.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zero.musichunter.data.database.DatabaseMusic
import com.zero.musichunter.data.database.MusicDatabase
import com.zero.musichunter.data.database.asDomainModel
import com.zero.musichunter.data.domain.MusicResults
import com.zero.musichunter.data.remote.MusicApiService
import com.zero.musichunter.data.remote.NetworkMusicContainer
import com.zero.musichunter.data.remote.asDatabaseModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber
import javax.inject.Inject

class MusicRepoImp @Inject constructor(
    private var musicApiService: MusicApiService,
    var database: MusicDatabase
) : MusicRepo {


    private var classicMusic: LiveData<List<MusicResults>> = Transformations.map(
        database.musicDao.getClassicMusic()
    ) {
        it.asDomainModel()
    }

    override fun classicMusicObserver(): LiveData<List<MusicResults>> = classicMusic


    override fun fetchClassicMusic(): Disposable {
        return musicApiService.getClassicMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(subscribeToDatabase())

    }

    private fun subscribeToDatabase(): DisposableSubscriber<in NetworkMusicContainer> {
        return object : DisposableSubscriber<NetworkMusicContainer>() {
            @SuppressLint("CheckResult")
            override fun onNext(networkMusicContainer: NetworkMusicContainer?) {
                if (networkMusicContainer != null) {
                    val entityList = networkMusicContainer.asDatabaseModel()
                    database.musicDao.insertAll(entityList)
                }

            }

            override fun onError(t: Throwable?) {
                Timber.e("MusicRepoImp error: ${t?.message}")
            }

            override fun onComplete() {
                Timber.v("insert success")
            }
        }

    }


}


//


//                //Fetch Pop Music from remote
//    override fun getPopMusic(): Observable<NetworkMusicContainer> =
//        musicApiService.getPopMusic()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    //Fetch Rock Music from remote
//    override fun getRockMusic(): Observable<NetworkMusicContainer> =
//        musicApiService.getRockMusic()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())


//                //Fetch Pop Music from remote
//    override fun getPopMusic(): Observable<NetworkMusicContainer> =
//        musicApiService.getPopMusic()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    //Fetch Rock Music from remote
//    override fun getRockMusic(): Observable<NetworkMusicContainer> =
//        musicApiService.getRockMusic()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())

