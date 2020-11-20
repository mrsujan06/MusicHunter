package com.zero.musichunter.domain.repository

import com.zero.musichunter.domain.model.Repo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RockRepository {

    val isConnected: Boolean

    fun getRockListFromNet(): Single<List<Repo>>

    fun getCacheRockListRepo(): Single<List<Repo>>

    fun saveRockListRepo(repoList: List<Repo>): Completable
}