package com.example.data.repository

import com.example.data.net.MusicApiService
import com.example.data.net.asDatabaseClassicModel
import com.example.data.net.asDatabasePopModel
import com.example.data.net.asDatabaseRockModel
import com.example.data.persistence.database.MusicDatabase
import com.example.data.persistence.entities.*
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepoRepositoryDataRepository @Inject constructor(
    private val musicApiService: MusicApiService, private val database: MusicDatabase
) : RepoRepository {

    init {
        fetchPopMusicFromNetwork()
        fetchRockMusicFromNetwork()
    }

    override fun getClassicListRepo(): Single<List<Repo>> {
        fetchClassicMusicFromNetwork()
        return database.musicDao.getClassicMusic()
            .map {
                it.asDomainModel()
            }

    }


    override fun getPopListRepo(): Single<List<Repo>> =
        database.musicDao.getPopMusic()
            .map {
                it.asDomainPopModel()
            }


    override fun getRockListRepo(): Single<List<Repo>> =
        database.musicDao.getRockMusic()
            .map {
                it.asDomainRockModel()
            }

    private fun fetchClassicMusicFromNetwork(): Disposable =
        musicApiService.getClassicMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertClassicToDatabase(it.asDatabaseClassicModel())
                TimberWrapper.d { "Insertion of classic music success" }
            }, {
                TimberWrapper.e { it }
            })

    private fun fetchPopMusicFromNetwork(): Disposable =
        musicApiService.getPopMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertPopToDatabase(it.asDatabasePopModel())
                TimberWrapper.d { "Insertion of pop music success" }
            }, {
                TimberWrapper.e { it }
            })

    private fun fetchRockMusicFromNetwork(): Disposable =
        musicApiService.getRockMusic().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                insertRockToDatabase(it.asDatabaseRockModel())
                TimberWrapper.d { "Insertion of pop music success" }
            }, {
                TimberWrapper.e { it }
            })


    // Insert classic music to database
    private fun insertClassicToDatabase(classicMusic: List<RepoClassicEntity>): Disposable {
        return database.musicDao.insertClassicMusic(classicMusic)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Classic music inserted to database")
                },
                {
                    Timber.d("Classic music insertion error : $it ")
                }
            )
    }

    // Insert pop music to database
    private fun insertPopToDatabase(popMusic: List<RepoPopEntity>): Disposable {
        return database.musicDao.insertPopMusic(popMusic)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Pop music inserted to database")
                },
                {
                    Timber.d("Pop music insertion error : $it ")
                }
            )
    }

    //Insert rock music to database
    private fun insertRockToDatabase(rockMusic: List<RepoRockEntity>): Disposable {
        return database.musicDao.insertRockMusic(rockMusic)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Rock music inserted to database")
                },
                {
                    Timber.d("Rock music insertion error : $it ")
                }
            )
    }


//    private val classicMusic: LiveData<List<Repo>> = Transformations.map(
//        database.musicDao.getClassicMusic()
//    ) { it.asDomainModel() }
//
//    private val popMusic: LiveData<List<Repo>> = Transformations.map(
//        database.musicDao.getPopMusic()
//    ) { it.asDomainPopModel() }
//
//    private val rockMusic: LiveData<List<Repo>> = Transformations.map(
//        database.musicDao.getRockMusic()
//    ) { it.asDomainRockModel() }


    //    override suspend fun getClassicMusic() {
//        withContext(Dispatchers.IO) {
//            Timber.d("getClassicMusic is called")
//            val playlist = musicApiService.getClassicMusic()
//            database.musicDao.insertAll(playlist.asDatabaseClassicModel())
//        }
//    }
//
//    override suspend fun getPopMusic() {
//        withContext(Dispatchers.IO) {
//            Timber.d("getPopMusic is called")
//            val playlist = musicApiService.getPopMusic()
//            database.musicDao.insertPopMusic(playlist.asDatabasePopModel())
//        }
//    }
//
//    override suspend fun getRockMusic() {
//        withContext(Dispatchers.IO) {
//            Timber.d("getPopMusic is called")
//            val playlist = musicApiService.getRockMusic()
//            database.musicDao.insertRockMusic(playlist.asDatabaseRockModel())
//        }
//    }


//    override fun classicMusicObservable(): LiveData<List<Repo>> = classicMusic
//
//    override fun popMusicObservable(): LiveData<List<Repo>> = popMusic
//
//    override fun rockMusicObservable(): LiveData<List<Repo>> = rockMusic

}