package com.zero.musichunter.domain.repository

import com.zero.musichunter.domain.model.Repo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ClassicRepository {

    val isConnected: Boolean

    //region LIST REPO
    fun getClassicListFromNet(): Single<List<Repo>>

    fun getCacheClassicListRepo(): Single<List<Repo>>

    fun saveClassicListRepo(repoList: List<Repo>): Completable
    //endregion
}