package com.ferraz.flashcard.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CollectionEntity(
    @PrimaryKey override var uuid: Long? = null,
    @ColumnInfo var name: String = "",
    @Ignore var cards: List<CardEntity>? = emptyList()
) : GenericEntity()