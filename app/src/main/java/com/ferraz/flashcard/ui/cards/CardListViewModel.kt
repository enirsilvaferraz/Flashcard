package com.ferraz.flashcard.ui.cards

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.usecases.GenericFindAllUseCase
import kotlinx.coroutines.launch

class CardListViewModel(private val findAllUseCase: GenericFindAllUseCase<CardEntity>) : ViewModel() {

    sealed interface State
    class Failure(val e: Exception) : State
    class Success(val models: List<CardEntity>) : State

    val cards = MediatorLiveData<State>().apply {
        viewModelScope.launch {
            try {
                addSource(findAllUseCase.execute()) {
                    postValue(Success(it ?: emptyList()))
                }
            } catch (e: Exception) {
                postValue(Failure(e))
            }
        }
    }
}