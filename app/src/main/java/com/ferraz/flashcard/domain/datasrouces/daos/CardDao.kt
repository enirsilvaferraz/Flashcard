package com.ferraz.flashcard.domain.datasrouces.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ferraz.flashcard.domain.entities.CardEntity

@Dao
abstract class CardDao : GenericDataSource<CardEntity>() {

    @Query("SELECT * FROM CardEntity")
    abstract override fun findAll(): LiveData<List<CardEntity>>

    @Query("SELECT * FROM CardEntity WHERE uuid = :uuid")
    abstract override fun findByID(uuid: Long?): LiveData<CardEntity>
}
