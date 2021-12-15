package com.ferraz.flashcard.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardEntity(val front: String, val back: String, val highlights: List<String>) : Parcelable