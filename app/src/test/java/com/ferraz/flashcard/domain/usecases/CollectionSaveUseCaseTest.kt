package com.ferraz.flashcard.domain.usecases

import com.ferraz.flashcard.domain.datasrouces.daos.AppDatabase.*
import com.ferraz.flashcard.domain.entities.CollectionEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CollectionSaveUseCaseTest {

    private val repository: GenericRepository<CollectionEntity> = mockk()

    private val useCase = CollectionSaveUseCase(repository = repository)

    @Test
    fun `DADO que nao preencho o nome da colecao QUANDO tento cadastra-la ENTAO deve retornar falha`() = runBlocking {

        // DADO
        val collection = CollectionEntity(name = "")

        // QUANDO
        val result = runCatching { useCase.execute(CollectionSaveUseCase.Params(collection)) }.exceptionOrNull()

        // ENTAO
        assert(result is CollectionSaveUseCase.NameNotFiledException)
    }

    @Test
    fun `DADO que preencho o nome da colecao QUANDO tento cadastra-la ENTAO deve retornar sucesso`() = runBlocking {

        // DADO
        coEvery { repository.save(any()) } returns CollectionEntity(uuid = 1, name = "Nome 1")
        val collection = CollectionEntity(name = "Nome 1")

        // QUANDO
        val result = runCatching { useCase.execute(CollectionSaveUseCase.Params(collection)) }.getOrNull()

        // ENTAO
        assert(result is CollectionEntity)
        assert(result?.uuid == 1L)
    }

    @Test
    fun `DADO que a cadastro da colecao nao funciona QUANDO tento cadastra-la ENTAO deve retornar falha`() = runBlocking {

        // DADO
        coEvery { repository.save(any()) } throws DataSourceException(Exception())
        val collection = CollectionEntity(name = "Nome 1")

        // QUANDO
        val result = runCatching { useCase.execute(CollectionSaveUseCase.Params(collection)) }.exceptionOrNull()

        // ENTAO
        assert(result is DataSourceException)
    }
}