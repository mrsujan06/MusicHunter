package com.example.data.repository

import com.example.data.di.providers.NetworkChecker
import com.example.data.mapper.RepoMapper
import com.example.data.net.api.MusicApiService
import com.example.data.persistence.processor.PopProcessor
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.PopRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable
import javax.inject.Inject

class PopRepositoryImp @Inject constructor(
    private val musicApiService: MusicApiService,
    private val popProcessor: PopProcessor,
    private val repoMapper: RepoMapper,
    private val networkChecker: NetworkChecker
) : PopRepository {
    override val isConnected: Boolean
        get() = networkChecker.isConnected

    override fun getPopListFromNet(): Single<List<Repo>> {
        return musicApiService.getPopMusic().map {
            repoMapper.transform(it.results)
        }
    }

    override fun getCachePopListRepo(): Single<List<Repo>> {
        return popProcessor.getAll()
            .map {
                repoMapper.transformPopToRepoList(it)
            }
    }

    override fun savePopListRepo(repoList: List<Repo>): Completable {
        return repoList.toObservable().flatMapCompletable {
            savePopRepo(it)
        }
    }

    private fun savePopRepo(repo: Repo): Completable {
        return popProcessor.get(repo.previewUrl)
            .defaultIfEmpty(repoMapper.transformToPopEntity(repo))
            .flatMapCompletable {
                popProcessor.insert(it)
            }
    }
}