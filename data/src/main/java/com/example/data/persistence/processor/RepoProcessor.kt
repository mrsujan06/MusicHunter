package com.example.data.persistence.processor

import com.example.data.persistence.dao.RepoDao
import com.example.data.persistence.entities.RepoClassicEntity
import com.example.data.persistence.entities.RepoPopEntity
import com.example.data.persistence.entities.RepoRockEntity
import com.zero.musichunter.domain.functions.CheckPersistenceResultFunction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepoProcessor @Inject internal constructor(private val dao: RepoDao) {

    fun getClassic(id: String): Maybe<RepoClassicEntity> =
        Maybe.fromCallable { dao.getClassic(id) }

    fun getAllClassic(): Single<List<RepoClassicEntity>> =
        Single.fromCallable { dao.getAllClassic() }

    fun getPop(id: String): Maybe<RepoPopEntity> =
        Maybe.fromCallable { dao.getPop(id) }

    fun getAllPop(): Single<List<RepoPopEntity>> =
        Single.fromCallable { dao.getAllPop() }

    fun getRock(id: String): Maybe<RepoRockEntity> =
        Maybe.fromCallable { dao.getRock(id) }

    fun getAllRock(): Single<List<RepoRockEntity>> =
        Single.fromCallable { dao.getAllRock() }

    fun insertClassic(repoClassicEntity: RepoClassicEntity): Completable {
        return Single.fromCallable {
            dao.insertClassic(repoClassicEntity) > 0
        }.flatMapCompletable(CheckPersistenceResultFunction())
    }

    fun insertPop(repoPopEntity: RepoPopEntity): Completable {
        return Single.fromCallable {
            dao.insertPop(repoPopEntity) > 0
        }.flatMapCompletable(CheckPersistenceResultFunction())

    }

    fun insertRock(repoRockEntity: RepoRockEntity): Completable {
        return Single.fromCallable {
            dao.insertRock(repoRockEntity) > 0
        }.flatMapCompletable(CheckPersistenceResultFunction())
    }
}


