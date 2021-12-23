package com.ferraz.flashcard.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CardEntity(
    @PrimaryKey override var uuid: Long? = null,
    @ColumnInfo val front: String = "",
    @ColumnInfo val back: String = "",
    //@Ignore val highlights: List<String>
) : GenericEntity()