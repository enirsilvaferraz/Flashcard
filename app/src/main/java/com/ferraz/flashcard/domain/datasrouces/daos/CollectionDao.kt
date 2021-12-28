package com.ferraz.flashcard.domain.datasrouces.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ferraz.flashcard.domain.entities.CollectionEntity

@Dao
abstract class CollectionDao : GenericDataSource<CollectionEntity>() {

    @Query("SELECT * FROM CollectionEntity")
    abstract override fun findAll(): LiveData<List<CollectionEntity>>

    @Query("SELECT * FROM CollectionEntity WHERE uuid = :uuid")
    abstract override fun findByID(uuid: Long?): LiveData<CollectionEntity>
}
