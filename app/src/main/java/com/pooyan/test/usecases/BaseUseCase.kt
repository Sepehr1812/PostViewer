package com.pooyan.test.usecases

/**
 * A basic use-case that only calls repository methods.
 */
abstract class BaseUseCase<in Q : BaseUseCase.RequestValue, R> {

    /**
     * Data passed to a request
     */
    interface RequestValue

    abstract suspend fun executeUseCase(requestValues: Q): R
}
