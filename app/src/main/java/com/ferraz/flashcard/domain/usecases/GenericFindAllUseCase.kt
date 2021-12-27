package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.entities.GenericEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository

class GenericFindAllUseCase<T : GenericEntity>(private val repository: GenericRepository<T>) {

    suspend fun execute() = repository.findAll()
}