package com.zero.musichunter.domain.functions

import com.zero.musichunter.domain.exception.PersistenceException
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.functions.Function

/**
 * [Function] that adds a delay to receiving
 * the onComplete when a [Single] return an error
 */
class CheckPersistenceResultFunction : Function<Boolean, Completable> {
    override fun apply(result: Boolean): Completable {
        return if (result) Completable.complete()
        else Completable.error(PersistenceException)
    }

}