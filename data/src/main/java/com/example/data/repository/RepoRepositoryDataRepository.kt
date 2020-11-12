package com.example.data.repository

import com.example.data.di.providers.NetworkChecker
import com.example.data.mapper.RepoMapper
import com.example.data.net.api.MusicApiService
import com.example.data.persistence.processor.RepoProcessor
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable
import javax.inject.Inject

class RepoRepositoryDataRepository @Inject constructor(
    private val musicApiService: MusicApiService,
    private val repoProcessor: RepoProcessor,
    private val repoMapper: RepoMapper,
    private val networkChecker: NetworkChecker
) : RepoRepository {

    override val isConnected: Boolean
        get() = networkChecker.isConnected

    override fun getClassicListFromNet(): Single<List<Repo>> {
        return musicApiService.getClassicMusic().map {
            repoMapper.transform(it.results)
        }
    }

    override fun getCacheClassicListRepo(): Single<List<Repo>> {
        return repoProcessor.getAllClassic()
            .map {
                repoMapper.transformClassicEntityToRepo(it)
            }
    }

    override fun getPopListFromNet(): Single<List<Repo>> {
        return musicApiService.getPopMusic().map {
            repoMapper.transform(it.results)
        }
    }

    override fun getCachePopListRepo(): Single<List<Repo>> {
        return repoProcessor.getAllPop()
            .map {
                repoMapper.transformPopToRepoList(it)
            }
    }

    override fun getRockListFromNet(): Single<List<Repo>> {
        return musicApiService.getRockMusic().map {
            repoMapper.transform(it.results)
        }
    }

    override fun getCacheRockListRepo(): Single<List<Repo>> {
        return repoProcessor.getAllRock().map {
            repoMapper.transformRockToRepoList(it)
        }
    }

    override fun saveClassicListRepo(repoList: List<Repo>): Completable {
        return repoList.toObservable().flatMapCompletable {
            saveClassicRepo(it)
        }
    }

    override fun savePopListRepo(repoList: List<Repo>): Completable {
        return repoList.toObservable().flatMapCompletable {
            savePopRepo(it)
        }
    }

    override fun saveRockListRepo(repoList: List<Repo>): Completable {
        return repoList.toObservable().flatMapCompletable {
            saveRockRepo(it)
        }
    }

    private fun saveClassicRepo(repo: Repo): Completable =
        repoProcessor.getClassic(repo.previewUrl)
            .defaultIfEmpty(repoMapper.transformToClassicEntity(repo))
            .flatMapCompletable {
                repoProcessor.insertClassic(it)
            }

    private fun savePopRepo(repo: Repo): Completable {
        return repoProcessor.getPop(repo.previewUrl)
            .defaultIfEmpty(repoMapper.transformToPopEntity(repo))
            .flatMapCompletable {
                repoProcessor.insertPop(it)
            }
    }

    private fun saveRockRepo(repo: Repo): Completable =
        repoProcessor.getRock(repo.previewUrl)
            .defaultIfEmpty(repoMapper.transformToRockEntity(repo))
            .flatMapCompletable {
                repoProcessor.insertRock(it)
            }


//    override fun getClassicListFromNet(): Single<List<Repo>> {
//        return musicApiService.getClassicMusic().map {
//            database.musicDao.insertClassicMusic(it.asDatabaseClassicModel())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    TimberWrapper.d { "Classic data inserted to db" }
//                }, {
//                    TimberWrapper.e { it }
//                })
//            it.asDomainModel()
//        }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun getPopListFromNet(): Single<List<Repo>> {
//        return musicApiService.getPopMusic().map {
//            database.musicDao.insertPopMusic(it.asDatabasePopModel())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    TimberWrapper.d { "Pop data inserted to db" }
//                }, {
//                    TimberWrapper.e { it }
//                })
//
//            it.asDomainModel()
//        }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun getRockListFromNet(): Single<List<Repo>> {
//        return musicApiService.getRockMusic().map {
//            database.musicDao.insertRockMusic(it.asDatabaseRockModel())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    TimberWrapper.d { "Rock data inserted to db" }
//                }, {
//                    TimberWrapper.e { it }
//                })
//
//            it.asDomainModel()
//        }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun getCacheClassicListRepo(): Single<List<Repo>> {
//        return database.musicDao.getClassicMusic()
//            .map {
//                it.asDomainModel()
//            }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun getCachePopListRepo(): Single<List<Repo>> =
//        database.musicDao.getPopMusic()
//            .map {
//                it.asDomainPopModel()
//            }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//
//    override fun getCacheRockListRepo(): Single<List<Repo>> =
//        database.musicDao.getRockMusic().map {
//            it.asDomainRockModel()
//        }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())

}