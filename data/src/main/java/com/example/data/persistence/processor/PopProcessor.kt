package com.example.data.persistence.processor

import com.example.data.persistence.dao.PopDao
import com.example.data.persistence.entities.PopEntity
import com.example.data.persistence.processor.base.BaseProcessor
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopProcessor
@Inject internal constructor(private val dao: PopDao) : BaseProcessor<PopEntity>(dao) {

    fun get(id: String): Maybe<PopEntity> =
        Maybe.fromCallable { dao.get(id) }

    fun getAll(): Single<List<PopEntity>> =
        Single.fromCallable { dao.getAll() }
}