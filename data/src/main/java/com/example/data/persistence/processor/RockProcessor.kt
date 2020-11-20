package com.example.data.persistence.processor

import com.example.data.persistence.dao.RockDao
import com.example.data.persistence.entities.RockEntity
import com.example.data.persistence.processor.base.BaseProcessor
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RockProcessor
@Inject internal constructor(private val dao: RockDao) : BaseProcessor<RockEntity>(dao) {

    fun get(id: String): Maybe<RockEntity> =
        Maybe.fromCallable { dao.get(id) }

    fun getAll(): Single<List<RockEntity>> =
        Single.fromCallable { dao.getAll() }
}