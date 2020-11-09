package com.zero.musichunter.domain.repository

import com.zero.musichunter.domain.model.Repo
import io.reactivex.Single

interface RepoRepository {

    //region LIST REPO
    fun getClassicListRepo(): Single<List<Repo>>

    fun getPopListRepo(): Single<List<Repo>>

    fun getRockListRepo(): Single<List<Repo>>
    //end region

}