package com.zero.musichunter.domain.functions

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.core.SingleSource

/**
 * @param <T> the common value type of the Single
 */
class SingleIfThen<T>(
    private val condition: Boolean,
    private val then: SingleSource<out T>,
    private val orElse: SingleSource<out T>
) : Single<T>() {

    override fun subscribeActual(observer: SingleObserver<in T>) {
        if (condition) {
            then.subscribe(observer)
        } else {
            orElse.subscribe(observer)
        }
    }
}