package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.entities.CollectionEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository

class CollectionSaveUseCase(private val repository: GenericRepository<CollectionEntity>) {

    class NameNotFiledException : Exception()

    data class Params(val model: CollectionEntity)

    suspend fun execute(params: Params) {
        if (params.model.name.isBlank()) throw NameNotFiledException()
        repository.save(params.model)
    }
}