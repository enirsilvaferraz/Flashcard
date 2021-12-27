package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository

class CardSaveUseCase(private val repository: GenericRepository<CardEntity>) : GenericUseCaseAbs<CardSaveUseCase.Params, CardEntity>() {

    class NameNotFiledException : Exception()

    data class Params(val entity: CardEntity)

   override suspend fun execute(params: Params): CardEntity {
        if (params.entity.front.isBlank()) throw NameNotFiledException()
        if (params.entity.back.isBlank()) throw NameNotFiledException()
        return repository.save(params.entity)
    }
}