package com.zero.musichunter.domain.usecases.base

import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<R>
constructor(
    private val useCaseScheduler: UseCaseScheduler?,
    private val logger: Logger?
) : UseCase<Single<R>>(logger) {

    override fun execute(fromUseCase: Boolean): Single<R> =
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
