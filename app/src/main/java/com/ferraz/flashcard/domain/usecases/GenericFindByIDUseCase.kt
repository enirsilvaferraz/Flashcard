package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.entities.GenericEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository
import com.ferraz.flashcard.domain.usecases.GenericFindByIDUseCase.Params

class GenericFindByIDUseCase<T : GenericEntity>(private val repository: GenericRepository<T>) {

    data class Params(val uuid: Long?)

    fun execute(params: Params) = repository.find(params.uuid)
}