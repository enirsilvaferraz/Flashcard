package com.ferraz.flashcard.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Test

class CollectionGetByIDlUseCaseTest {

    @Test
    fun `DADO que nao passo o id da colecao QUANDO consulto as colecoes ENTAO devera retornar todas as colecoes`() = runBlocking {
        assert(false)
    }

    @Test
    fun `DADO que passo o id da colecao QUANDO consulto as colecoes ENTAO devera retornar uma unica colecao`() = runBlocking {
        assert(false)
    }

    @Test
    fun `DADO que nao tenho colecoes cadastradas QUANDO consulto as colecoes ENTAO devera retornar uma lista vazia`() = runBlocking {
        assert(false)
    }

    @Test
    fun `DADO que ha uma falha no datasource QUANDO consulto as colecoes ENTAO devera retornar uma excessao`() = runBlocking {
        assert(false)
    }
}