package com.ferraz.flashcard.domain.datasrouces.daos

import androidx.room.Dao
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.entities.CollectionEntity

@Dao
abstract class CardDao : GenericDataSource<CardEntity>(CardEntity::class.simpleName)
