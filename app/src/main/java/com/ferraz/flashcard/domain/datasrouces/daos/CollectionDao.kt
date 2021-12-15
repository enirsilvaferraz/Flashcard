package com.ferraz.flashcard.domain.datasrouces.daos

import androidx.room.Dao
import com.ferraz.flashcard.domain.entities.CollectionEntity

@Dao
abstract class CollectionDao : GenericDataSource<CollectionEntity>(CollectionEntity::class.simpleName)
