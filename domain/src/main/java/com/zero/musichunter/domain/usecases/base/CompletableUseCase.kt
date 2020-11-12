package com.zero.musichunter.domain.usecases.base

import io.reactivex.rxjava3.core.Completable

abstract class CompletableUseCase
constructor(
    private val useCaseScheduler: UseCaseScheduler?,
    private val logger: Logger?
) : UseCase<Completable>(logger) {

    override fun execute( fromUseCase: Boolean): Completable =
        super.execute(fromUseCase)
            .compose { transformer ->
                useCaseScheduler?.let {
                    if (fromUseCase) transformer
                    else transformer.subscribeOn(it.run).observeOn(it.post)
                } ?: transformer
            }
            .doOnError { logger?.logError { it } }
            .doOnComplete { logger?.log { "${javaClass.simpleName} : completed" } }

}
