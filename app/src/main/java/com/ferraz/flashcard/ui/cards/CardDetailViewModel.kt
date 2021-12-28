package com.ferraz.flashcard.ui.cards

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.usecases.CardSaveUseCase
import com.ferraz.flashcard.domain.usecases.GenericFindByIDUseCase
import kotlinx.coroutines.launch

class CardDetailViewModel(
    private val findByIdUseCase: GenericFindByIDUseCase<CardEntity>,
    private val saveUseCase: CardSaveUseCase,
) : ViewModel() {

    sealed interface State
    class Failure(val e: Exception) : State
    class Success<T>(val data: T) : State

    val saveLiveData = MutableLiveData<State>()

    fun getData(uuid: Long? = null) = MediatorLiveData<State>().apply {
        try {
            addSource(findByIdUseCase.execute(params = GenericFindByIDUseCase.Params(uuid = uuid))) { card ->
                postValue(Success(card))
            }
        } catch (e: Exception) {
            postValue(Failure(e))
        }
    }

    fun save(entity: CardEntity) {
        try {
            viewModelScope.launch {
                saveUseCase.execute(CardSaveUseCase.Params(entity))
                saveLiveData.postValue(Success(Unit))
            }
        } catch (e: Exception) {
            saveLiveData.postValue(Failure(e))
        }
    }
}
