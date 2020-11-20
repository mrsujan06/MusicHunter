package com.example.data.repository

import com.example.data.di.providers.NetworkChecker
import com.example.data.mapper.RepoMapper
import com.example.data.net.api.MusicApiService
import com.example.data.persistence.processor.RockProcessor
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RockRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable
import javax.inject.Inject

class RockRepositoryImp @Inject constructor(
    private val musicApiService: MusicApiService,
    private val rockProcessor: RockProcessor,
    private val repoMapper: RepoMapper,
    private val networkChecker: NetworkChecker
) : RockRepository {

    override val isConnected: Boolean
        get() = networkChecker.isConnected

    override fun getRockListFromNet(): Single<List<Repo>> {
        return musicApiService.getRockMusic().map {
            repoMapper.transform(it.results)
        }
    }

    override fun getCacheRockListRepo(): Single<List<Repo>> {
        return rockProcessor.getAll().map {
            repoMapper.transformRockToRepoList(it)
        }
    }

    override fun saveRockListRepo(repoList: List<Repo>): Completable {
        return repoList.toObservable().flatMapCompletable {
            saveRockRepo(it)
        }
    }

    private fun saveRockRepo(repo: Repo): Completable =
        rockProcessor.get(repo.previewUrl)
            .defaultIfEmpty(repoMapper.transformToRockEntity(repo))
            .flatMapCompletable {
                rockProcessor.insert(it)
            }
}
