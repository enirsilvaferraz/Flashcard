package com.ferraz.flashcard.domain.repositories

import androidx.lifecycle.LiveData
import com.ferraz.flashcard.domain.entities.GenericEntity

interface GenericRepository<T : GenericEntity> {

    suspend fun save(model: T)
    suspend fun delete(model: T)
    suspend fun find2(uuid: Long?): T
    fun find(uuid: Long?): LiveData<T>
    fun findAll(): LiveData<List<T>>
}

