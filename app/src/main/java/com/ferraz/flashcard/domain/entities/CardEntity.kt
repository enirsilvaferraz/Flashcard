package com.ferraz.flashcard.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardEntity(
    @PrimaryKey override var uuid: Long? = null,
    @ColumnInfo val front: String,
    @ColumnInfo val back: String,
    @Ignore val highlights: List<String>
) : GenericEntity()