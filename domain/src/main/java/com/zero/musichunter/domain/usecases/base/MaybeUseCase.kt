package com.zero.musichunter.domain.usecases.base

import io.reactivex.rxjava3.core.Maybe

abstract class MaybeUseCase<R>
constructor(
    private val useCaseScheduler: UseCaseScheduler?,
    private val logger: Logger?
) : UseCase<Maybe<R>>(logger) {

    override fun execute( fromUseCase: Boolean): Maybe<R> =
        super.execute(fromUseCase)
            .compose { transformer ->
                useCaseScheduler?.let {
                    if (fromUseCase) transformer
                    else transformer.subscribeOn(it.run).observeOn(it.post)
                } ?: transformer
            }
            .doOnError { logger?.logError { it } }
            .doOnSuccess { logger?.log { "${javaClass.simpleName} : $it" } }

}
