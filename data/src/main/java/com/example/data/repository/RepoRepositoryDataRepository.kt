package com.example.data.repository

import com.example.data.di.providers.NetworkChecker
import com.example.data.net.api.MusicApiService
import com.example.data.net.dto.asDatabaseClassicModel
import com.example.data.net.dto.asDatabasePopModel
import com.example.data.net.dto.asDatabaseRockModel
import com.example.data.net.dto.asDomainModel
import com.example.data.persistence.database.MusicDatabase
import com.example.data.persistence.entities.asDomainModel
import com.example.data.persistence.entities.asDomainPopModel
import com.example.data.persistence.entities.asDomainRockModel
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoRepositoryDataRepository @Inject constructor(
    private val musicApiService: MusicApiService,
    val database: MusicDatabase, private val networkChecker: NetworkChecker
) : RepoRepository {

    override val isConnected: Boolean
        get() = networkChecker.isConnected

    override fun getClassicListFromNet(): Single<List<Repo>> {
        return musicApiService.getClassicMusic().map {
            database.musicDao.insertClassicMusic(it.asDatabaseClassicModel())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    TimberWrapper.d { "Classic data inserted to db" }
                }, {
                    TimberWrapper.e { it }
                })
            it.asDomainModel()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPopListFromNet(): Single<List<Repo>> {
        return musicApiService.getPopMusic().map {
            database.musicDao.insertPopMusic(it.asDatabasePopModel())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    TimberWrapper.d { "Pop data inserted to db" }
                }, {
                    TimberWrapper.e { it }
                })

            it.asDomainModel()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRockListFromNet(): Single<List<Repo>> {
        return musicApiService.getRockMusic().map {
            database.musicDao.insertRockMusic(it.asDatabaseRockModel())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    TimberWrapper.d { "Rock data inserted to db" }
                }, {
                    TimberWrapper.e { it }
                })

            it.asDomainModel()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCacheClassicListRepo(): Single<List<Repo>> {
        return database.musicDao.getClassicMusic()
            .map {
                it.asDomainModel()
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCachePopListRepo(): Single<List<Repo>> =
        database.musicDao.getPopMusic()
            .map {
                it.asDomainPopModel()
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getCacheRockListRepo(): Single<List<Repo>> =
        database.musicDao.getRockMusic().map {
            it.asDomainRockModel()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}