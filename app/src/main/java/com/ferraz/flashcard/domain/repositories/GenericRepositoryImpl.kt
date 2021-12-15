package com.ferraz.flashcard.domain.repositories

import com.ferraz.flashcard.domain.datasrouces.daos.AppDatabase
import com.ferraz.flashcard.domain.datasrouces.daos.GenericDataSource
import com.ferraz.flashcard.domain.entities.GenericEntity

class GenericRepositoryImpl<T : GenericEntity>(private val datasource: GenericDataSource<T>) : GenericRepository<T> {

    override suspend fun save(model: T): T = safeCall {
        datasource.findByID(if (model.uuid == null) datasource.save(model) else datasource.update(model).toLong())
    }

    override suspend fun delete(model: T) = safeCall {
        datasource.delete(model)
    }

    override suspend fun find(uuid: Long): T = safeCall {
        datasource.findByID(uuid)
    }

    override suspend fun findAll(): List<T> = safeCall {
        datasource.findAll()
    }

    private suspend fun <T> safeCall(function: suspend () -> T): T = try {
        function()
    } catch (e: Exception) {
        throw AppDatabase.DataSourceException(e)
    }
}