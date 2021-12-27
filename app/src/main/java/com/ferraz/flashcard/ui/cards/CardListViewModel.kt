package com.ferraz.flashcard.ui.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.usecases.GenericFindAllUseCase
import kotlinx.coroutines.launch

class CardListViewModel(private val findAllUseCase: GenericFindAllUseCase<CardEntity>?) : ViewModel() {

    var all by mutableStateOf(listOf<CardEntity>())

    init {
        viewModelScope.launch {
            all = findAllUseCase?.execute() ?: emptyList()
        }
    }
}