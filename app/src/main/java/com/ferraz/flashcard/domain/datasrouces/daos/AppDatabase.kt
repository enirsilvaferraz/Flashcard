package com.ferraz.flashcard.domain.datasrouces.daos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.domain.entities.CollectionEntity

@Database(entities = [CardEntity::class, CollectionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun collectionDao(): CollectionDao

    abstract fun cardDao(): CardDao

    companion object {
        fun getDatabaseInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "flashcard-db").build()
    }

    class DataSourceException(e: Exception) : Exception(e)
}