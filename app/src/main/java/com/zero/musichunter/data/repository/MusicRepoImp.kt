package com.zero.musichunter.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zero.musichunter.data.database.*
import com.zero.musichunter.data.domain.MusicResults
import com.zero.musichunter.data.remote.MusicApiService
import com.zero.musichunter.data.remote.asDatabaseClassicModel
import com.zero.musichunter.data.remote.asDatabasePopModel
import com.zero.musichunter.data.remote.asDatabaseRockModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class MusicRepoImp @Inject constructor(
    private var musicApiService: MusicApiService,
    var database: MusicDatabase
) : MusicRepo {

    private var classicMusic: LiveData<List<MusicResults>> = Transformations.map(
        database.musicDao.getClassicMusic()
    ) {
        it.asDomainClassicModel()
    }

    private var popMusic: LiveData<List<MusicResults>> = Transformations.map(
        database.musicDao.getPopMusic()
    ) {
        it.asDomainPopModel()
    }

    private var rockMusic: LiveData<List<MusicResults>> = Transformations.map(
        database.musicDao.getRockMusic()
    ) {
        it.asDomainRockModel()
    }

    override fun classicMusicObserver(): LiveData<List<MusicResults>> = classicMusic

    override fun popMusicObserver(): LiveData<List<MusicResults>> = popMusic

    override fun rockMusicObserver(): LiveData<List<MusicResults>> = rockMusic

    /**
     * Fetching  classic music from Network and Inserting the data to the database
     * */
    override fun fetchClassicMusic(): Disposable {
        return musicApiService.getClassicMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ networkMusicContainer ->
                if (networkMusicContainer != null) {
                    val entityList = networkMusicContainer.asDatabaseClassicModel()
                    insertClassicToDatabase(entityList)
                }
            }, {
                Timber.e("Error while fetching : $it")
            })

    }

    /**
     * Fetching  pop music from Network and Inserting the data to the database
     * */
    override fun fetchPopMusic(): Disposable {
        return musicApiService.getPopMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { networkMusicContainer ->
                    if (networkMusicContainer != null) {
                        val entityList = networkMusicContainer.asDatabasePopModel()
                        insertPopToDatabase(entityList)
                    }
                }, {
                    Timber.e("Error while fetching pop music : $it")
                }
            )
    }

    /**
     * Fetching  rock music from Network and Inserting the data to the database
     * */
    override fun fetchRockMusic(): Disposable {
        return musicApiService.getRockMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { networkMusicContainer ->
                    if (networkMusicContainer != null) {
                        val entityList = networkMusicContainer.asDatabaseRockModel()
                        insertRockToDatabase(entityList)
                    }
                }, {
                    Timber.e("Error while fetching : $it")
                }
            )
    }

    // Insert classic music to database
    private fun insertClassicToDatabase(classicMusic: List<DatabaseClassicMusic>): Disposable {
        return database.musicDao.insertClassicMusic(classicMusic)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Timber.d("Classic music inserted to database") },
                { Timber.d("Classic music insertion error : $it ") }
            )
    }

    // Insert pop music to database
    private fun insertPopToDatabase(popMusic: List<DatabasePopMusic>): Disposable {
        return database.musicDao.insertPopMusic(popMusic)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Timber.d("Pop music inserted to database") },
                { Timber.d("Pop music insertion error : $it ") }
            )
    }

    //Insert rock music to database
    private fun insertRockToDatabase(rockMusic: List<DatabaseRockMusic>): Disposable {
        return database.musicDao.insertRockMusic(rockMusic)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Timber.d("Rock music inserted to database") },
                { Timber.d("Rock music insertion error : $it") }
            )
    }

}