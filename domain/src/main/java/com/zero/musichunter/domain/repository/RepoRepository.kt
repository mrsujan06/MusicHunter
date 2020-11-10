package com.zero.musichunter.domain.repository

import com.zero.musichunter.domain.model.Repo
import io.reactivex.Single

interface RepoRepository {


    val isConnected: Boolean

    //region LIST REPO
    fun getClassicListFromNet(): Single<List<Repo>>

    fun getPopListFromNet(): Single<List<Repo>>

    fun getRockListFromNet(): Single<List<Repo>>

    fun getCacheClassicListRepo(): Single<List<Repo>>

    fun getCachePopListRepo(): Single<List<Repo>>

    fun getCacheRockListRepo(): Single<List<Repo>>
    //end region

}