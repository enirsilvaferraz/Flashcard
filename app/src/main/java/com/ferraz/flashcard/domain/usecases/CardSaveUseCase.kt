package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository

class CardSaveUseCase(private val repository: GenericRepository<CardEntity>) {

    class NameNotFiledException : Exception()

    data class Params(val entity: CardEntity)

    suspend fun execute(params: Params) {
        if (params.entity.front.isBlank()) throw NameNotFiledException()
        if (params.entity.back.isBlank()) throw NameNotFiledException()
        repository.save(params.entity)
    }
}