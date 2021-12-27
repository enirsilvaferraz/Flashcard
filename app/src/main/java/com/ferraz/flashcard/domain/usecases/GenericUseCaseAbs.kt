package com.ferraz.flashcard.domain.usecases

abstract class GenericUseCaseAbs<in P, out T> {

    abstract suspend fun execute(params: P): T
}