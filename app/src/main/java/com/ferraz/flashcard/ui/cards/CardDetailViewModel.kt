package com.ferraz.flashcard.ui.cards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.usecases.CardSaveUseCase
import com.ferraz.flashcard.domain.usecases.GenericFindByIDUseCase
import kotlinx.coroutines.launch

class CardDetailViewModel(
    private val findByIdUseCase: GenericFindByIDUseCase<CardEntity>,
    private val saveUseCase: CardSaveUseCase
) : ViewModel() {

    val entity = MutableLiveData<CardEntity>()

    fun getByID(uuid: Long?) {
        viewModelScope.launch {
            entity.value = uuid?.let { findByIdUseCase.execute(params = GenericFindByIDUseCase.Params(uuid = uuid)) } ?: CardEntity()
        }
    }

    fun save(entity: CardEntity) {
        viewModelScope.launch {
            saveUseCase.execute(CardSaveUseCase.Params(entity))
        }
    }
}
