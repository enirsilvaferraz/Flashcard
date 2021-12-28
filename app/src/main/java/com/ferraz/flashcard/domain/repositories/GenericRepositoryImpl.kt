package com.ferraz.flashcard.domain.repositories

import androidx.lifecycle.LiveData
import com.ferraz.flashcard.domain.datasrouces.daos.AppDatabase
import com.ferraz.flashcard.domain.datasrouces.daos.GenericDataSource
import com.ferraz.flashcard.domain.entities.GenericEntity

class GenericRepositoryImpl<T : GenericEntity>(private val datasource: GenericDataSource<T>) : GenericRepository<T> {

    override suspend fun save(model: T) {
        try {
            if (model.uuid == null) datasource.save(model)
            else datasource.update(model).toLong()
        } catch (e: Exception) {
            throw AppDatabase.DataSourceException(e)
        }
    }

    override suspend fun delete(model: T) = safeCall {
        datasource.delete(model)
    }

    override suspend fun find2(uuid: Long?) = safeCall {
        datasource.findByID2(uuid)
    }

    override fun find(uuid: Long?) = safeCallLiveData {
        datasource.findByID(uuid)
    }

    override fun findAll() = safeCallLiveData {
        datasource.findAll()
    }

    private suspend fun <T> safeCall(function: suspend () -> T): T = try {
        function()
    } catch (e: Exception) {
        throw AppDatabase.DataSourceException(e)
    }

    private fun <T> safeCallLiveData(function: () -> LiveData<T>): LiveData<T> = try {
        function()
    } catch (e: Exception) {
        throw AppDatabase.DataSourceException(e)
    }
}