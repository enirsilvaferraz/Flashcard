package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.entities.CollectionEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository

class CollectionGetAllUseCase(private val repository: GenericRepository<CollectionEntity>) {

    suspend fun execute(): List<CollectionEntity> = repository.findAll()
}