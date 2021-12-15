package com.ferraz.flashcard.domain.repositories

import com.ferraz.flashcard.domain.entities.GenericEntity

interface GenericRepository<T : GenericEntity> {

    suspend fun save(model: T): T
    suspend fun delete(model: T)
    suspend fun find(uuid: Long): T
    suspend fun findAll(): List<T>
}

