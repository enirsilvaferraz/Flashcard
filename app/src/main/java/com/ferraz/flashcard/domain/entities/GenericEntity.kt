package com.ferraz.flashcard.domain.entities

import android.os.Parcelable

abstract class GenericEntity : Parcelable {
    open var uuid: Long? = null
}