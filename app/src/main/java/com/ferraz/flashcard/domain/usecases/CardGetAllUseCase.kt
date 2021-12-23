package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository

class CardGetAllUseCase(private val repository: GenericRepository<CardEntity>) {

    suspend fun execute() = repository.findAll()
}