package com.ferraz.flashcard.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import com.ferraz.flashcard.ui.cards.CardDetailView
import com.ferraz.flashcard.ui.cards.CardDetailViewModel
import com.ferraz.flashcard.ui.cards.CardListView
import com.ferraz.flashcard.ui.cards.CardListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiKoinModules {

    val viewmodels = module {

        viewModel { CardListViewModel(findAllUseCase = get()) }
        viewModel { CardDetailViewModel(findByIdUseCase = get(), saveUseCase = get()) }
    }

    @ExperimentalFoundationApi
    val views = module {

        factory { CardListView(viewModel = get()) }
        factory { CardDetailView(viewModel = get()) }
    }
}