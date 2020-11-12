package com.zero.musichunter.domain.repository

import com.zero.musichunter.domain.model.Repo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RepoRepository {

    val isConnected: Boolean

    //region LIST REPO
    fun getClassicListFromNet(): Single<List<Repo>>

    fun getCacheClassicListRepo(): Single<List<Repo>>

    fun getPopListFromNet(): Single<List<Repo>>

    fun getCachePopListRepo(): Single<List<Repo>>

    fun getRockListFromNet(): Single<List<Repo>>

    fun getCacheRockListRepo(): Single<List<Repo>>

    fun saveClassicListRepo(repoList: List<Repo>): Completable

    fun savePopListRepo(repoList: List<Repo>): Completable

    fun saveRockListRepo(repoList: List<Repo>): Completable

    //end region

}