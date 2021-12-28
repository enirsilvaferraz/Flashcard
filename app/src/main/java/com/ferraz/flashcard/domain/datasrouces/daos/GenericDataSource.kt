package com.ferraz.flashcard.domain.datasrouces.daos

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.ferraz.flashcard.domain.entities.GenericEntity
import java.lang.reflect.ParameterizedType

abstract class GenericDataSource<T : GenericEntity> {

    private val table = ((javaClass.superclass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>).simpleName

    @Insert
    abstract suspend fun save(model: T): Long

    @Update
    abstract suspend fun update(model: T): Int

    @Delete
    abstract suspend fun delete(model: T)

    //@RawQuery
    //protected abstract suspend fun findAll(query: SupportSQLiteQuery): List<T>

    @RawQuery
    protected abstract suspend fun findByID2(query: SupportSQLiteQuery): T

    //suspend fun findAll() = findAll(SimpleSQLiteQuery("SELECT * FROM $table"))

    suspend fun findByID2(uuid: Long?) = findByID2(SimpleSQLiteQuery("SELECT * FROM $table WHERE uuid = $uuid"))

    abstract fun findAll(): LiveData<List<T>>

    abstract fun findByID(uuid: Long?): LiveData<T>
}