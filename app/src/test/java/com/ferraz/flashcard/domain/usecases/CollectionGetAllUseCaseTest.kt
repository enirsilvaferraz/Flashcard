package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.datasrouces.daos.AppDatabase
import com.ferraz.flashcard.domain.entities.CollectionEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.Exception

class CollectionGetAllUseCaseTest {

    private val repository: GenericRepository<CollectionEntity> = mockk()
    private val usecase = CollectionGetAllUseCase(repository = repository)

    @Test
    fun `DADO que tenho colecoes salvas QUANDO consulto as colecoes ENTAO devera retornar todas as colecoes`() = runBlocking {

        // DADO
        coEvery { repository.findAll() } returns listOf(mockk(), mockk(), mockk())

        // QUANDO
        val result = runCatching { usecase.execute() }.getOrNull()

        // ENTAO
        assert(result?.size == 3)
    }

    @Test
    fun `DADO que nao tenho colecoes salvas QUANDO consulto as colecoes ENTAO devera retornar uma lista vazia`() = runBlocking {

        // DADO
        coEvery { repository.findAll() } returns emptyList()

        // QUANDO
        val result = runCatching { usecase.execute() }.getOrNull()

        // ENTAO
        assert(result?.size == 0)
    }

    @Test
    fun `DADO que ha uma falha no datasource QUANDO consulto as colecoes ENTAO devera retornar uma excessao`() = runBlocking {

        // DADO
        coEvery { repository.findAll() } throws AppDatabase.DataSourceException(Exception())

        // QUANDO
        val result = runCatching { usecase.execute() }.exceptionOrNull()

        // ENTAO
        assert(result is AppDatabase.DataSourceException)
    }
}