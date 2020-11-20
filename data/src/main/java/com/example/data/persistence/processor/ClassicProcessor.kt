package com.example.data.persistence.processor

import com.example.data.persistence.dao.ClassicDao
import com.example.data.persistence.entities.ClassicEntity
import com.example.data.persistence.processor.base.BaseProcessor
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassicProcessor
@Inject internal constructor(private val dao: ClassicDao) : BaseProcessor<ClassicEntity>(dao) {

    fun get(id: String): Maybe<ClassicEntity> =
        Maybe.fromCallable { dao.get(id) }

    fun getAll(): Single<List<ClassicEntity>> =
        Single.fromCallable { dao.getAll() }
}