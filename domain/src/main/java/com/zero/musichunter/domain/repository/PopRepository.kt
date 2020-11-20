package com.zero.musichunter.domain.repository

import com.zero.musichunter.domain.model.Repo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PopRepository {

    val isConnected: Boolean

    fun getPopListFromNet(): Single<List<Repo>>

    fun getCachePopListRepo(): Single<List<Repo>>

    fun savePopListRepo(repoList: List<Repo>): Completable
}