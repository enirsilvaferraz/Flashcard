package com.ferraz.flashcard.domain

import com.ferraz.flashcard.domain.datasrouces.daos.AppDatabase
import com.ferraz.flashcard.domain.datasrouces.daos.GenericDataSource
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.repositories.GenericRepository
import com.ferraz.flashcard.domain.repositories.GenericRepositoryImpl
import com.ferraz.flashcard.domain.usecases.CardSaveUseCase
import com.ferraz.flashcard.domain.usecases.GenericFindAllUseCase
import com.ferraz.flashcard.domain.usecases.GenericFindByIDUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DomainKoinModule {

    val datasources = module {

        single { AppDatabase.getDatabaseInstance(context = androidContext()) }

        single<GenericDataSource<CardEntity>> { get<AppDatabase>().cardDao() }
    }

    val repositories = module {

        factory<GenericRepository<CardEntity>> { GenericRepositoryImpl(datasource = get()) }
    }

    val useCases = module {

        factory { GenericFindAllUseCase<CardEntity>(repository = get()) }
        factory { GenericFindByIDUseCase<CardEntity>(repository = get()) }
        factory { CardSaveUseCase(repository = get()) }
    }
}