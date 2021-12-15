package com.ferraz.flashcard.domain.repositories

import android.database.sqlite.SQLiteConstraintException
import com.ferraz.flashcard.domain.datasrouces.daos.AppDatabase.DataSourceException
import com.ferraz.flashcard.domain.datasrouces.daos.CollectionDao
import com.ferraz.flashcard.domain.entities.CollectionEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GenericRepositoryImplTest {

    private val datasource: CollectionDao = mockk()

    private val repository: GenericRepository<CollectionEntity> = GenericRepositoryImpl(datasource)

    /**
     * SAVE
     */

    @Test
    fun `DADO que tenho uma colecao nova QUANDO tento cadastra-la ENTAO deve retornar sucesso`() = runBlocking {

        coEvery { datasource.save(any()) } returns 1
        coEvery { datasource.findByID(any()) } returns CollectionEntity(uuid = 1, name = "Nome 1")

        // DADO
        val collection = CollectionEntity(uuid = null, name = "Nome 1")

        // QUANDO
        val result = runCatching { repository.save(collection) }.getOrNull()

        // ENTAO
        assert(result is CollectionEntity)
        assert(result?.uuid == 1L)
        assert(result?.name == "Nome 1")
    }

    @Test
    fun `DADO que a base de dados falha ao cadastrar uma colecao QUANDO tento cadastra-la ENTAO deve retornar falha`() = runBlocking {

        coEvery { datasource.save(any()) } throws SQLiteConstraintException()

        // DADO
        val collection = CollectionEntity(uuid = null, name = "Nome 1")

        // QUANDO
        val result = runCatching { repository.save(collection) }.exceptionOrNull()

        // ENTAO
        assert(result is DataSourceException)
    }

    /**
     * UPDATE
     */

    @Test
    fun `DADO que tenho uma colecao ja cadastrada QUANDO tento atualiza-la ENTAO deve retornar sucesso`() = runBlocking {

        coEvery { datasource.update(any()) } returns 1
        coEvery { datasource.findByID(any()) } returns CollectionEntity(uuid = 1, name = "Nome 1")

        // DADO
        val collection = CollectionEntity(uuid = 1, name = "Nome 1")

        // QUANDO
        val result = runCatching { repository.save(collection) }.getOrNull()

        // ENTAO
        assert(result is CollectionEntity)
        assert(result?.uuid == 1L)
        assert(result?.name == "Nome 1")
    }

    @Test
    fun `DADO que a base de dados falha ao cadastrar uma colecao QUANDO tento atualiza-la ENTAO deve retornar falha`() = runBlocking {

        coEvery { datasource.update(any()) } throws SQLiteConstraintException()

        // DADO
        val collection = CollectionEntity(uuid = 1, name = "Nome 1")

        // QUANDO
        val result = runCatching { repository.save(collection) }.exceptionOrNull()

        // ENTAO
        assert(result is DataSourceException)
    }

    /**
     * FIND ALL
     */

    @Test
    fun `DADO que tenho colecoes cadastradas QUANDO busco todas ENTAO deve retornar uma lista de colecoes`() = runBlocking {

        // DADO
        coEvery { datasource.findAll() } returns listOf(CollectionEntity(uuid = 1, name = "Name"))

        // QUANDO
        val result = runCatching { repository.findAll() }.getOrNull()

        // ENTAO
        assert(result?.isNotEmpty() ?: false)
    }

    @Test
    fun `DADO que a base de dados falha ao buscar colecoes QUANDO busco todas ENTAO deve retornar falha`() = runBlocking {

        // DADO
        coEvery { datasource.findAll() } throws Exception()

        // QUANDO
        val result = runCatching { repository.findAll() }.exceptionOrNull()

        // ENTAO
        assert(result is DataSourceException)
    }

    /**
     * FIND
     */

    @Test
    fun `DADO que tenho colecoes cadastradas QUANDO busco por ID ENTAO deve retornar uma lista de colecoes`() = runBlocking {

        // DADO
        coEvery { datasource.findByID(any()) } returns CollectionEntity(uuid = 1, name = "Name")

        // QUANDO
        val result = runCatching { repository.find(1) }.getOrNull()

        // ENTAO
        assert(result != null)
    }

    @Test
    fun `DADO que a base de dados falha ao buscar colecoes QUANDO busco por ID ENTAO deve retornar falha`() = runBlocking {

        // DADO
        coEvery { datasource.findAll() } throws Exception()

        // QUANDO
        val result = runCatching { repository.find(1) }.exceptionOrNull()

        // ENTAO
        assert(result is DataSourceException)
    }
}