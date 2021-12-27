package com.ferraz.flashcard.domain.datasrouces.daos

import android.database.sqlite.SQLiteConstraintException
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.room.Room
import com.ferraz.flashcard.domain.entities.CollectionEntity
import com.ferraz.flashcard.ui.AppApplicationTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@ExperimentalFoundationApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = AppApplicationTest::class)
class CollectionDaoTest {

    private lateinit var dao: CollectionDao

    @Before
    fun setUp() {
        dao = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.getApplication(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build().collectionDao()
    }

    /**
     * SAVE
     */

    @Test
    fun `DADO que nao cadastrei colecoes QUANDO tento cadastra-la ENTAO devera recupera-la do banco`() = runBlocking {

        // DADO
        val collection = CollectionEntity(name = "Nome")

        // QUANDO
        dao.save(collection)

        // ENTAO
        with(dao.findAll()) {
            assert(size == 1)
            assert(this[0].uuid != null)
            assert(this[0].name == collection.name)
        }
    }

    @Test
    fun `DADO que cadastrei colecoes QUANDO tento cadastra-la ENTAO devera retornar falha`() = runBlocking {

        // DADO
        dao.save(CollectionEntity(name = "Nome"))
        val collection = dao.findAll().first().copy(name = "Nome 2")

        // QUANDO
        val result = runCatching { dao.save(collection) }.exceptionOrNull()

        // ENTAO
        assert(result is SQLiteConstraintException)
    }

    /**
     * UPDATE
     */

    @Test
    fun `DADO que cadastrei colecoes QUANDO tento edita-la ENTAO devera recupera-la do banco`() = runBlocking {

        // DADO
        dao.save(CollectionEntity(name = "Nome"))
        val collection = dao.findAll().first().copy(name = "Nome 2")

        // QUANDO
        val result = runCatching { dao.update(collection) }.getOrNull()

        // ENTAO
        with(dao.findByID(result!!.toLong())) {
            assert(uuid == collection.uuid)
            assert(name == collection.name)
        }
    }

    @Test
    fun `DADO que nao cadastrei colecoes QUANDO tento edita-la ENTAO nao sera atualizado`() = runBlocking {

        // DADO
        val collection = CollectionEntity(uuid = 1, name = "Nome")

        // QUANDO
        val result = runCatching { dao.update(collection) }.getOrNull()

        // ENTAO
        assert(result == 0)
    }

    /**
     * FIND ALL
     */

    @Test
    fun `DADO que cadastrei colecoes QUANDO tento busca-las ENTAO deve retornar uma lista de colecoes`() = runBlocking {

        // DADO
        dao.save(CollectionEntity(name = "Nome 1"))
        dao.save(CollectionEntity(name = "Nome 2"))
        dao.save(CollectionEntity(name = "Nome 3"))

        // QUANDO
        val result = runCatching { dao.findAll() }.getOrNull()

        // ENTAO
        assert(result?.size == 3)
    }

    @Test
    fun `DADO que nao cadastrei colecoes QUANDO tento busca-la ENTAO deve retornar uma lista vazia`() = runBlocking {

        // QUANDO
        val result = runCatching { dao.findAll() }.getOrNull()

        // ENTAO
        assert(result?.isEmpty() ?: false)
    }

    /**
     * FIND UNIQUE
     */

    @Test
    fun `DADO que cadastrei colecoes QUANDO tento buscar por ID ENTAO deve retorna-la`() = runBlocking {

        // DADO
        dao.save(CollectionEntity(name = "Nome 1"))
        dao.save(CollectionEntity(name = "Nome 2"))
        dao.save(CollectionEntity(name = "Nome 3"))

        // QUANDO
        val result = runCatching { dao.findByID(1) }.getOrNull()

        // ENTAO
        assert(result?.name == "Nome 1")
    }

    @Test
    fun `DADO que nao cadastrei colecoes QUANDO tento buscar por ID ENTAO deve retornar uma lista vazia`() = runBlocking {

        // QUANDO
        val result = runCatching { dao.findAll() }.getOrNull()

        // ENTAO
        assert(result?.isEmpty() ?: false)
    }
}