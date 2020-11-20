package com.example.data.repository

import com.example.data.di.providers.NetworkChecker
import com.example.data.mapper.RepoMapper
import com.example.data.net.api.MusicApiService
import com.example.data.persistence.processor.ClassicProcessor
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.ClassicRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable
import javax.inject.Inject

class ClassicRepositoryImp @Inject constructor(
    private val musicApiService: MusicApiService,
    private val classicProcessor: ClassicProcessor,
    private val repoMapper: RepoMapper,
    private val networkChecker: NetworkChecker
) : ClassicRepository {
    override val isConnected: Boolean
        get() = networkChecker.isConnected

    override fun getClassicListFromNet(): Single<List<Repo>> {
        return musicApiService.getClassicMusic().map {
            repoMapper.transform(it.results)
        }
    }

    override fun getCacheClassicListRepo(): Single<List<Repo>> {
        return classicProcessor.getAll()
            .map {
                repoMapper.transformClassicEntityToRepo(it)
            }
    }

    override fun saveClassicListRepo(repoList: List<Repo>): Completable {
        return repoList.toObservable().flatMapCompletable {
            saveClassicRepo(it)
        }
    }

    private fun saveClassicRepo(repo: Repo): Completable {
        return classicProcessor.get(repo.previewUrl)
            .defaultIfEmpty(repoMapper.transformToClassicEntity(repo))
            .flatMapCompletable {
                classicProcessor.insert(it)
            }
    }
}