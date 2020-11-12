package com.zero.musichunter.domain.usecases.base

import org.reactivestreams.Subscriber

/**
 * Abstract class for a Use Case
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each Use Case implementation will return the result using a [Subscriber]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<R>(private val logger: Logger?) {

    /**
     * Builds which will be used when executing the current [UseCase].
     */
    protected abstract fun build(): R

    /**
     * Executes the current use case.
     */
    fun execute(): R = execute(false)

    /**
     * To not apply transformer with [UseCaseScheduler]
     * This method can be used just in domain module (internal).
     */
   // internal fun executeFromAnOtherUseCase(param: P): R = execute(true)

    protected open fun execute(fromUseCase: Boolean): R {
        logger?.log { "${javaClass.simpleName} : $fromUseCase" }
        return build()
    }

}